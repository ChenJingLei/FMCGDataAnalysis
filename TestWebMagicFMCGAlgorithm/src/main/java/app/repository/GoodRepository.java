package app.repository;

import app.model.Good;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cjl20 on 2016/10/29.
 */
public interface GoodRepository extends CrudRepository<Good, Long> {

    Good findOneByWareId(String wareId);

}
