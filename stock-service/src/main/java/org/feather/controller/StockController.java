package org.feather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-04 20:59
 **/
@RestController
public class StockController {
    @GetMapping("stock/reduce/{productId}")
    public  String reduce(@PathVariable Integer productId){
        System.out.println("减库存成功个"+productId+"个");
        return  "减库存成功个"+productId+"个";
    }
}
