package com.youtubemetricapp.youtubesearch.Controller;

import com.rabbitmq.client.*;
import com.youtubemetricapp.youtubesearch.Model.QueueAModel;
import com.youtubemetricapp.youtubesearch.Model.QueueBModel;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
public class QueueBController {

    @GetMapping(value = "/queueB")
    public List<String> getQueueB() throws JSONException, IOException, TimeoutException {
        QueueAModel.getQueueA();
        return QueueBModel.getQueueB();
    }
}
