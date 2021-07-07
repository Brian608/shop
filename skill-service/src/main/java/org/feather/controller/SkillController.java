package org.feather.controller;

import org.feather.service.SkillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-07 21:16
 **/
@RestController
public class SkillController {
    @Autowired
    private SkillGoodsService skillGoodsService;

    @GetMapping("/skill")
    public  String skill(Long productId,String userId){
        try {
            skillGoodsService.add(productId,userId);
           return  "秒杀成功";
        } catch (Exception e) {
            return  e.getMessage();
        }
    }
}
