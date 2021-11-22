package com.youtubemetricapp.youtubesearch.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YtApiService {

    private YtApiService () {}

    //function to get raw data from youtube api and store into a list of hashmap
    static public List<Map<String,Object>> getrawyt(String key, String query) throws JSONException, JsonProcessingException {
        var mapper = new ObjectMapper();
        String url = String.format("https://youtube.googleapis.com/youtube/v3/search?part=snippet&q=%s&key=%s",query, key);
        RestTemplate restTemplate = new RestTemplate();
        String ytData = restTemplate.getForObject(url, String.class);
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {};
        Map<String, Object> searchObjs = new ObjectMapper().readValue(ytData, typeRef);
        Map<String, String> searchpageInfo = new ObjectMapper().convertValue(searchObjs.get("pageInfo"), Map.class);
        //used to get all the results of "telecom" from api however seems that max results is 50
        url = String.format("https://youtube.googleapis.com/youtube/v3/search?part=snippet&q=%s&maxResults=%s&key=%s", query,searchpageInfo.get("totalResults"),key);
        ytData = restTemplate.getForObject(url, String.class);
        searchObjs = new ObjectMapper().readValue(ytData, typeRef);
        List<Map<String, Object>> listsearchObjs = new ObjectMapper().convertValue(searchObjs.get("items"), List.class);
        return listsearchObjs;
    }
}
