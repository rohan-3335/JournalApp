package com.SpringBootProject.journalApp.services;


import com.SpringBootProject.journalApp.api.response.WeatherResponse;
import com.SpringBootProject.journalApp.cache.AppCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {

//
//    @Value(("$(weatherApi"))
//    private   String apikey;
//
@Value("${weather.api.key}")
private String apikey;


//    private static final  String API = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY";

      @Autowired
    private RestTemplate restTemplate;
      @Autowired
      public AppCache appCache;

      @Autowired
      private RedisService redisService;

      public WeatherResponse callApi(String city) {

          WeatherResponse weatherResponse = redisService.get("Weather of " +city, WeatherResponse.class);

          if(weatherResponse != null) {
              log.info("It is working");

           return weatherResponse;
          }
          else {
              String finalApi = appCache.APP_CACHE.get("weatherApi").replace("<apiKey>", apikey).replace("<city>", city);
              ResponseEntity<WeatherResponse> res = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
              WeatherResponse body = res.getBody();
              if(body != null){
                  redisService.set("Weather of " +city,body,300l);
              }

              return body;
          }
      }

}
