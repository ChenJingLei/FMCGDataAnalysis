package app.repositories;

import app.model.ProductItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by cjl20 on 2016/7/5.
 */
public interface ProductItemRepository extends CrudRepository<ProductItem,String> {

    List<ProductItem> findAll();
}
