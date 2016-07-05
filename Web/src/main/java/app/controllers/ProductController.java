package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cjl20 on 2016/7/6.
 */
@Controller
public class ProductController {

    @RequestMapping(value = "/product/productlist", method = RequestMethod.GET)
    public String showProductList() {
        return "views/product/productlist";
    }

    @RequestMapping(value = "/product/productdetail", method = RequestMethod.GET)
    public String showProductDetail() {
        return "views/product/productdetail";
    }

}
