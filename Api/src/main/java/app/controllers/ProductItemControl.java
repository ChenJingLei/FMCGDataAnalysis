package app.controllers;

import app.model.ProductItem;
import app.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cjl20 on 2016/7/5.
 */
@RestController
@RequestMapping(value = "/product")

public class ProductItemControl {

    @Autowired
    private ProductItemRepository productItemRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<ProductItem> getAll() {
        return productItemRepository.findAll();
    }

    @RequestMapping(value = "/getId/{id}", method = RequestMethod.GET)
    public ProductItem getId(@PathVariable("id") String id) {
        return productItemRepository.findOne(id);
    }


}
