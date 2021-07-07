package org.feather.service;

import org.feather.entity.SkillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-07 07:30
 **/
@Service
public class ProductService {
    @Autowired
    private RestTemplate restTemplate;

    public SkillGoods queryByProductId(Long productId){
        return  restTemplate.getForObject("http://product-service/product/"+productId,SkillGoods.class);
    }

    public  void  update(SkillGoods skillGoods){
        ResponseEntity <String> result=restTemplate.postForEntity("http://product-service/product/update",skillGoods,String.class);
        System.out.println(result.getBody());
    }
}
