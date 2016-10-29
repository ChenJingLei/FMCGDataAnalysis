import app.model.ProductItem;
import app.repositories.ProductItemRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by cjl20 on 2016/7/7.
 */

public class UnitTestCase extends TestCase {

    @Autowired
    private ProductItemRepository productItemRepository;

    ProductItem productItem;

    @PostConstruct
    public void init() {
        productItem = new ProductItem();
        productItem.setProductId("111");
        productItem.setBrand("http://test/1111");
        productItem.setDetailUrl("http://test/1111");
        productItem.setEvaluateNum(1);
        productItem.setPrice(1.0);
        productItem.setProductSiteId("1");
        productItem.setTitle("TEST");
        productItem.setBrand("test");
        productItem.setImgUrl("http://test/1111");
        productItem.setSaleNum(1);
        productItem.setEvaluateNum(1);
        productItem.setSpecification("test");
        productItem.setPlatform("1");
        productItem.setTime(new Date().getTime());
        productItem.setProductTypeId(1);
    }


    @Test
    public void testAdd() {
        productItemRepository.save(productItem);
        assertEquals(productItem.getTitle(), productItemRepository.findOne("111").getTitle());
    }

    @Test
    public void testUpdate() {

        productItem.setBrand("2222");
        productItemRepository.save(productItem);
        assertEquals("2222", productItemRepository.findOne("111").getBrand());

        productItem.setDetailUrl("http://test/1111");
        productItemRepository.save(productItem);
        assertEquals("http://test/1111", productItemRepository.findOne("111").getDetailUrl());

        productItem.setEvaluateNum(1);
        productItemRepository.save(productItem);
        assertEquals(1, productItemRepository.findOne("111").getEvaluateNum());

        productItem.setPrice(1.0);
        productItemRepository.save(productItem);
        assertEquals(1.0, productItemRepository.findOne("111").getPrice());

        productItem.setProductSiteId("1");
        productItemRepository.save(productItem);
        assertEquals("1", productItemRepository.findOne("111").getProductSiteId());

        productItem.setTitle("TEST");
        productItemRepository.save(productItem);
        assertEquals("TEST", productItemRepository.findOne("111").getTitle());

        productItem.setImgUrl("http://test/1111");
        productItemRepository.save(productItem);
        assertEquals(2.0, productItemRepository.findOne("111").getPrice());

        productItem.setSaleNum(1);
        productItemRepository.save(productItem);
        assertEquals(2.0, productItemRepository.findOne("111").getPrice());

        productItem.setEvaluateNum(1);
        productItemRepository.save(productItem);
        assertEquals(2.0, productItemRepository.findOne("111").getPrice());

        productItem.setSpecification("test");
        productItemRepository.save(productItem);
        assertEquals("test", productItemRepository.findOne("111").getSpecification());

        productItem.setPlatform("1");
        productItemRepository.save(productItem);
        assertEquals("1", productItemRepository.findOne("111").getPlatform());

        long time = new Date().getTime();
        productItem.setTime(time);
        productItemRepository.save(productItem);
        assertEquals(time, productItemRepository.findOne("111").getTime());

        productItem.setProductTypeId(1);
        productItemRepository.save(productItem);
        assertEquals(1, Math.toIntExact(productItemRepository.findOne("111").getProductTypeId()));
    }

    @Test
    public void testDelete() {
        productItemRepository.save(productItem);

        productItemRepository.delete("111");

        assertEquals(false, productItemRepository.exists("111"));
    }

}
