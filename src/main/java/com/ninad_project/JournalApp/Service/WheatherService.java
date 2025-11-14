package com.ninad_project.JournalApp.Service;

import com.ninad_project.JournalApp.ApiResponce.WheatherResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WheatherService {
    private static final String apiKey = "209bfc0715778969417ad88622626b23";
    private static final String API= "http://api.weatherstack.com/current?access_key=" + "API_KEY"+ "&query=" + "CITY";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WheatherResponce getWheather(String city){
        WheatherResponce wheatherResponce = redisService.get("weather_of_" + city, WheatherResponce.class);
        if(wheatherResponce != null){
            return wheatherResponce;
        }else{
            String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
            ResponseEntity<WheatherResponce> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WheatherResponce.class);
            WheatherResponce body = response.getBody();
            if(body != null){
               redisService.set("weather_of_" + city,body,300l);
            }
            return body;
        }

    }
}
