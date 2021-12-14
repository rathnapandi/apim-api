package com.axway.apimcassandra;

import com.axway.apimcassandra.model.ApplicationEntity;
import com.axway.apimcassandra.repo.ApplicationRepository;
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
public class ApplicationController {

    private  final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    private ApplicationRepository applicationRepository;
    public ApplicationController(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    @GetMapping(value = "/applications", produces = MediaType.APPLICATION_JSON_VALUE )

    public ResponseEntity<List<ApplicationEntity>> getApplications(@RequestParam(required = false) String name,
                                                                   @RequestParam(defaultValue = "3") int size, @RequestHeader(value = "PageState", required = false)String pagingState ){
        try {
            if (name == null) {
                ByteBuffer pagingStateObj = pagingState != null ? Base64.getDecoder().decode(ByteBuffer.wrap(pagingState.getBytes())) : null;
                Pageable pageable = CassandraPageRequest.of(PageRequest.of(0,  size), pagingStateObj);
                Slice<ApplicationEntity> slice = applicationRepository.findAll(pageable);
                if(slice.isLast()) {
                    pagingState = "-1";
                }else{
                    CassandraPageRequest nextPageable = (CassandraPageRequest) slice.nextPageable();
                    pagingState = new String((Base64.getEncoder().encode(nextPageable.getPagingState()).array()));
                    logger.info("Paging state : {}", pagingState);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.add("PageState", pagingState);
                return new ResponseEntity<>(applicationRepository.findAll(pageable).getContent(), headers, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(applicationRepository.findByName(name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/applications/{id}")
    public ApplicationEntity getApplicationById(@PathVariable("id") String id) {
        return applicationRepository.findById(id).get();
    }
}
