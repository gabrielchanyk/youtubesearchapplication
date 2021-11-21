package com.youtubemetricapp.youtubesearch.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.youtubemetricapp.youtubesearch.Model.YtApiService;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class YtApiController {

    @GetMapping(value = "/callrawyt")
    private List<Map<String,Object>> getrawyt() throws JSONException, JsonProcessingException {
        return YtApiService.getrawyt("AIzaSyApCoSCJ9Uvnfksl7RSiFHzgwoE7Nr7aYA","telecom");
    }
}
