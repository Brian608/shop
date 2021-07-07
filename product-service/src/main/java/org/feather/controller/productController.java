package org.feather.controller;

import org.feather.entity.SkillGoods;
import org.feather.service.SkillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-04 09:24
 **/
@RestController
public class productController {

    @Autowired
    private SkillGoodsService skillGoodsService;

    @GetMapping("/product/{productId}")
    public SkillGoods getProduct(@PathVariable Long productId){
        System.out.println("调用商品服务");
        return skillGoodsService.queryProduct(productId);
    }

    @PostMapping("/product/update")
    public  String update(@RequestBody  SkillGoods skillGoods){
        skillGoodsService.updateProduct(skillGoods);
        return  "更新库存成功";
    }
}
