package app;

import app.model.DataApi;
import app.model.Query;
import app.model.QueryResponse;
import com.google.gson.Gson;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cjl20 on 2016/7/4.
 */
@SpringBootApplication
public class TestApplication implements CommandLineRunner{
    public static void main(String[] args){
        SpringApplication.run(TestApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        DataApi dataApi = new DataApi("200010","100a0b10603e1a453dd84743123029ab");
        Query query = new Query();
        query.setAppkey(dataApi.getAppKey());
        query.setKeyword(URLEncoder.encode("i","UTF-8"));
        query.setSiteId(1);
        query.setTimeStamp(new Date().getTime());
        query.setPageIndex(1);
        query.setPageSize(100);
        query.setSign(query.generateSign(dataApi.getAppSecret()));
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        Gson gson = new Gson();
        HttpEntity request = new HttpEntity(gson.toJson(query),headers);
        String responseStr = restTemplate.postForObject("http://api.simplybrand.com/QueryProductService/Query",request, String.class);
        QueryResponse response = gson.fromJson(responseStr,QueryResponse.class);
        System.out.println(response.getResponseResult().get(0).getPrice());
    }
}
