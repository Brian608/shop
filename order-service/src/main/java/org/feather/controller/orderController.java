package org.feather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-03 20:41
 **/
@RestController
public class orderController {

    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/create/order")
    public String createOrder(String  userid,String productId){
        String productName = restTemplate.getForObject("http://127.0.0.1:9000/product/" + productId, String.class);
        return  "用户ID:"+userid+",商品ID:"+productName;
    }
}
