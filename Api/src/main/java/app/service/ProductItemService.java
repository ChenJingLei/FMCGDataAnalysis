package app.service;

import app.model.DataApi;
import app.model.Query;
import app.model.QueryResponse;
import app.repositories.ProductItemRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by cjl20 on 2016/7/5.
 */
@Service
public class ProductItemService {

    @Autowired
    private ProductItemRepository productItemRepository;

    @PostConstruct
    public void init() {

    }

    public void CaptureData() throws Exception {
        DataApi dataApi = new DataApi("200010","100a0b10603e1a453dd84743123029ab");
        Query query = new Query();
        query.setAppkey(dataApi.getAppKey());
        query.setKeyword(URLEncoder.encode("iphone6","UTF-8"));
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
//        System.out.println(responseStr);
        QueryResponse response = gson.fromJson(responseStr,QueryResponse.class);
//        System.out.println(response.getResponseResult().get(0).toString());
        productItemRepository.save(response.getResponseResult());

    }

    @PreDestroy
    public void destory() {

    }


}
