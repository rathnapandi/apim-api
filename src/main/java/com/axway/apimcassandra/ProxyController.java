package com.axway.apimcassandra;

import com.axway.apimcassandra.model.APIDependency;
import com.axway.apimcassandra.model.FrontendAPI;
import com.axway.apimcassandra.repo.ProxyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1.4")
public class ProxyController {

    private  final Logger logger = LoggerFactory.getLogger(ProxyController.class);



    private APIManager apiManager;
    private ProxyRepository proxyRepository;

    public ProxyController(APIManager apiManager, ProxyRepository proxyRepository){
        this.apiManager = apiManager;
        this.proxyRepository = proxyRepository;
    }

    @GetMapping(value = "/proxiesByName", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<FrontendAPI> getProxyByName(@RequestParam String name){
        return proxyRepository.findByName(name);
    }

    @GetMapping(value = "/proxies", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<FrontendAPI>> getProxies(
            @RequestParam(defaultValue = "10") int size, @RequestHeader(value = "PageState", required = false) String pagingState) {
        try {

            ByteBuffer pagingStateObj = pagingState != null ? Base64.getDecoder().decode(ByteBuffer.wrap(pagingState.getBytes())) : null;
            Pageable pageable = CassandraPageRequest.of(PageRequest.of(0, size), pagingStateObj);
            Slice<FrontendAPI> slice = proxyRepository.findAll(pageable);
            if (slice.isLast()) {
                pagingState = "-1";
            } else {
                CassandraPageRequest nextPageable = (CassandraPageRequest) slice.nextPageable();
                pagingState = new String((Base64.getEncoder().encode(nextPageable.getPagingState()).array()));
                logger.info("Paging state : {}", pagingState);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("PageState", pagingState);
            return new ResponseEntity<>(slice.getContent(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/proxies/access", produces = MediaType.APPLICATION_JSON_VALUE )
    public APIDependency getProxyDependencyByName(@RequestParam String name){
        return apiManager.getProxyDependencyByName(name);
    }
}
