package org.feather.service;

import org.feather.dao.SkillOrderRepository;
import org.feather.entity.SkillGoods;
import org.feather.entity.SkillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-07 21:01
 **/
@Service
public class SkillGoodsService {
    public  static  final String SKILL_GOODS_PHONE="SKILL_GOODS_PHONE";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SkillOrderRepository skillOrderRepository;

    @Autowired
    private ProductService productService;

    public  void  add(Long productId,String  userId) throws Exception {
        SkillGoods skillGoods = productService.queryByProductId(productId);
        if (skillGoods==null){
            throw  new Exception("商品已经被秒杀光了");
        }
        if (skillGoods.getStockCount()>0){
            SkillOrder skillOrder=new SkillOrder();
            skillOrder.setMoney(skillGoods.getCostPrice());
            skillOrder.setPaytime(new Date());
            skillOrder.setStatus("0");
            skillOrder.setUserId(userId);
            skillOrder.setCreateTime(new Date());
            skillOrder.setSkillId(productId);
            skillOrderRepository.save(skillOrder);
            skillGoods.setStockCount(skillGoods.getStockCount()-1);
            redisTemplate.boundHashOps(SKILL_GOODS_PHONE).put(skillGoods.getId(),skillGoods);
            System.out.println("秒杀成功，剩余库存为："+skillGoods.getStockCount());
        }
        if (skillGoods.getStockCount()<=0){
            System.out.println("库存已经是负数了"+skillGoods.getStockCount());
            redisTemplate.boundHashOps(SKILL_GOODS_PHONE).delete(skillGoods.getId());
            productService.update(skillGoods);
        }
    }
}
