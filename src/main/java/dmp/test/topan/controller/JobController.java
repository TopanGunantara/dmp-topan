package dmp.test.topan.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dmp.test.topan.entity.JobResponse;

@RestController
public class JobController {
    private static final String JOB_SRC_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
    private static final String JOB_DETAIL_SRC_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions/";

    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/positions")
    public List<JobResponse> positions(){
        List<JobResponse> result = new ArrayList<>();
        try{
            JsonNode jsonNode = mapper.readTree(new URL(JOB_SRC_URL));
            result = mapper.readValue(jsonNode.toString(), new TypeReference<List<JobResponse>>(){});
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        return result;
    }

    @GetMapping("/positions/{id}")
    public JobResponse positionsById(@PathVariable String id){
        JobResponse result = new JobResponse();
        try{
            JsonNode jsonNode = mapper.readTree(new URL(JOB_DETAIL_SRC_URL+id));
            result = mapper.readValue(jsonNode.toString(), JobResponse.class);
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        return result;
    }
}