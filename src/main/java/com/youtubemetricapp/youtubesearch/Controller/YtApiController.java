package com.youtubemetricapp.youtubesearch.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.youtubemetricapp.youtubesearch.Model.YtApiService;
import com.youtubemetricapp.youtubesearch.classes.Credentials;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class YtApiController {

    //query that can change if no longer want to use "telecom" titles
    String query = "telecom";
    //raw Youtube REST API data page used by Queue A to get Youtube video information
    @GetMapping(value = "/callrawyt")
    private List<Map<String,Object>> getrawyt() throws JSONException, JsonProcessingException {
        return YtApiService.getrawyt(Credentials.apikey,query);
    }
}
