package app.repositories;

import app.model.ProductType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cjl20 on 2016/7/5.
 */
public interface ProductTypeRepository extends CrudRepository<ProductType,Integer> {
}
