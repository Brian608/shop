package org.feather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-04 09:24
 **/
@RestController
public class productController {

    @GetMapping("/product/{productId}")
    public  String getProduct(@PathVariable Integer productId){
        return "mac book"+productId;
    }
}
