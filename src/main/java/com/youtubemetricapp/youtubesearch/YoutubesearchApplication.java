package com.youtubemetricapp.youtubesearch;

import com.youtubemetricapp.youtubesearch.Model.QueueAModel;
import com.youtubemetricapp.youtubesearch.Model.QueueBModel;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class YoutubesearchApplication {


	public static void main(String[] args) throws JSONException, IOException, TimeoutException {
		SpringApplication.run(YoutubesearchApplication.class, args);
	}
}
