package app.repositories;

import app.model.ProductItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cjl20 on 2016/7/5.
 */
public interface ProductItemRepository extends CrudRepository<ProductItem,String> {
}
