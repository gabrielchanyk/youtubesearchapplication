package com.youtubemetricapp.youtubesearch.Controller;

import com.youtubemetricapp.youtubesearch.Model.QueueAModel;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
public class QueueAController {


    //queue A rest API page with XML messages saved onto JMS Queue A
    @GetMapping(value = "/queueA")
    public List<String> getQueueA() throws JSONException, IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        return QueueAModel.getQueueA();
    }

}
