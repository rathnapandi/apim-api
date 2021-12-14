package com.axway.apimcassandra;

import com.axway.apimcassandra.model.Summary;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.4")

public class SummaryController {

    private APIManager apiManager;

    public SummaryController(APIManager apiManager){
        this.apiManager = apiManager;
    }

    @GetMapping(value = "/summaries", produces = MediaType.APPLICATION_JSON_VALUE )
    public Summary getSummary(){
        return apiManager.getSummary();
    }
}
