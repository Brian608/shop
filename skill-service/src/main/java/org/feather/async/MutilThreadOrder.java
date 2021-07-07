package org.feather.async;

import org.feather.dao.SkillOrderRepository;
import org.feather.entity.SkillEntity;
import org.feather.entity.SkillGoods;
import org.feather.entity.SkillOrder;
import org.feather.service.ProductService;
import org.feather.service.SkillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: EShopParent
 * @description:多线程异步抢单队列
 * @author: 杜雪松(feather)
 * @create: 2021-07-07 21:41
 **/
@Component
public class MutilThreadOrder {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SkillOrderRepository skillOrderRepository;

    @Autowired
    private ProductService productService;

    @Async
    public  void  createOrder() throws Exception {
        System.out.println("开始异步抢单");
        SkillEntity skillEntity=(SkillEntity) redisTemplate.boundListOps(SkillGoodsService.SKILL_GOODS_LIST).rightPop();
        if (skillEntity==null){
            return;
        }
        Long productId=skillEntity.getProductId();
        String userId=skillEntity.getUserId();
        Thread.sleep(1000);
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
            redisTemplate.boundHashOps(SkillGoodsService.SKILL_GOODS_PHONE).put(skillGoods.getId(),skillGoods);
            System.out.println("秒杀成功，剩余库存为："+skillGoods.getStockCount());
        }
        if (skillGoods.getStockCount()<=0){
            System.out.println("库存已经是负数了"+skillGoods.getStockCount());
            redisTemplate.boundHashOps(SkillGoodsService.SKILL_GOODS_PHONE).delete(skillGoods.getId());
            productService.update(skillGoods);
        }

        System.out.println("结束异步抢单");
    }
}
