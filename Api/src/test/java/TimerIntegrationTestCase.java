import app.model.DataApi;
import app.model.ProductItem;
import app.model.Query;
import app.model.QueryResponse;
import app.repositories.ProductItemRepository;
import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by cjl20 on 2016/7/7.
 */

public class TimerIntegrationTestCase extends TestCase {

    @Autowired
    private ProductItemRepository productItemRepository;

    /*
    * 对象集成
    */
    @Test
    public void testTimerMainSoft() {
        DataApi dataApi = new DataApi("200010", "100a0b10603e1a453dd84743123029ab");
        Query query = new Query();
        query.setAppkey(dataApi.getAppKey());
        query.setKeyword("iphone");
        query.setSiteId(1);
        query.setTimeStamp(new Date().getTime());
        query.setPageIndex(1);
        query.setPageSize(100);
        query.setSign("test123456");

        //*****DataApi对象测试****
        assertEquals("200010", dataApi.getAppKey());
        assertEquals("100a0b10603e1a453dd84743123029ab", dataApi.getAppSecret());

        //*****Query对象测试****
        assertEquals("200010", query.getAppkey());
        assertEquals("iphone", query.getKeyword());
        assertEquals(1, query.getSiteId());
        assertEquals(new Date().getTime(), query.getTimeStamp());
        assertEquals(1, query.getPageIndex());
        assertEquals(100, query.getPageSize());
        assertEquals("test123456", query.getSign());
    }

    /*
    * 属性集成
    */
    @Test
    public void testGenerateSign() throws Exception {
        DataApi dataApi = new DataApi("200010", "100a0b10603e1a453dd84743123029ab");
        Query query = new Query();
        query.setAppkey(dataApi.getAppKey());
        query.setKeyword("iphone");
        query.setSiteId(1);
        query.setTimeStamp(1467830250914L);
        query.setPageIndex(1);
        query.setPageSize(100);

        assertEquals("f60fb8dd3d7e56e9fa05eea08401f4de", query.generateSign(dataApi.getAppSecret()));
    }

    /*
    * 属性集成
    */
    @Test
    public void testQuerySign() throws Exception {
        DataApi dataApi = new DataApi("200010", "100a0b10603e1a453dd84743123029ab");
        Query query = new Query();
        query.setAppkey(dataApi.getAppKey());
        query.setKeyword("iphone");
        query.setSiteId(1);
        query.setTimeStamp(1467830250914L);
        query.setPageIndex(1);
        query.setPageSize(100);
        query.setSign(query.generateSign(dataApi.getAppSecret()));

        assertEquals("f60fb8dd3d7e56e9fa05eea08401f4de", query.getSign());
    }

    /*
   * 接口连通性测试
   */
    @Test
    public void testRest() throws Exception {
        DataApi dataApi = new DataApi("200010", "100a0b10603e1a453dd84743123029ab");
        Query query = new Query();
        query.setAppkey(dataApi.getAppKey());
        query.setKeyword("iphone");
        query.setSiteId(1);
        query.setTimeStamp(1467830250914L);
        query.setPageIndex(1);
        query.setPageSize(100);
        query.setSign(query.generateSign(dataApi.getAppSecret()));
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        Gson gson = new Gson();
        HttpEntity request = new HttpEntity(gson.toJson(query), headers);
        String responseStr = restTemplate.postForObject("http://api.simplybrand.com/QueryProductService/Query", request, String.class);

        assertEquals(false, responseStr.trim().equals(""));
    }


    /*
   * 接口正确性测试测试
   */
    @Test
    public void testApi() throws Exception {
        DataApi dataApi = new DataApi("200010", "100a0b10603e1a453dd84743123029ab");
        Query query = new Query();
        query.setAppkey(dataApi.getAppKey());
        query.setKeyword("iphone");
        query.setSiteId(1);
        query.setTimeStamp(1467830250914L);
        query.setPageIndex(1);
        query.setPageSize(100);
        query.setSign(query.generateSign(dataApi.getAppSecret()));
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        Gson gson = new Gson();
        HttpEntity request = new HttpEntity(gson.toJson(query), headers);
        String responseStr = restTemplate.postForObject("http://api.simplybrand.com/QueryProductService/Query", request, String.class);
        Gson g = new Gson();
        assertEquals(null, g.fromJson(responseStr, QueryResponse.class).getErrorMsg());
    }

    /*
 * 接口正确性测试测试
 */
    @Test
    public void testSQL() throws Exception {
        DataApi dataApi = new DataApi("200010", "100a0b10603e1a453dd84743123029ab");
        Query query = new Query();
        query.setAppkey(dataApi.getAppKey());
        query.setKeyword("iphone");
        query.setSiteId(1);
        query.setTimeStamp(1467830250914L);
        query.setPageIndex(1);
        query.setPageSize(100);
        query.setSign(query.generateSign(dataApi.getAppSecret()));
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        Gson gson = new Gson();
        HttpEntity request = new HttpEntity(gson.toJson(query), headers);
        String responseStr = restTemplate.postForObject("http://api.simplybrand.com/QueryProductService/Query", request, String.class);
        Gson g = new Gson();
        QueryResponse response = gson.fromJson(responseStr,QueryResponse.class);
        productItemRepository.deleteAll();
        Iterable<ProductItem> productItems =  productItemRepository.save(response.getResponseResult());
        while (productItems.iterator().hasNext()){
            ProductItem productItem = productItems.iterator().next();
            System.out.println(productItem.toString());
        }

        assertEquals(null, g.fromJson(responseStr, QueryResponse.class).getErrorMsg());
    }


}
