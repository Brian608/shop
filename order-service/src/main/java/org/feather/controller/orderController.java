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
    @RequestMapping("/order/create")
    public String createOrder(Integer productId,String userId){
        String result = restTemplate.getForObject("http://stock-service/stock/reduce/" + productId, String.class);
        return result;
    }
}
