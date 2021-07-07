package org.feather.service;

import org.feather.async.MutilThreadOrder;
import org.feather.dao.SkillOrderRepository;
import org.feather.entity.SkillEntity;
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

    public  static  final String SKILL_GOODS_LIST="SKILL_GOODS_LIST";
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SkillOrderRepository skillOrderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private MutilThreadOrder mutilThreadOrder;

    public  void  add(Long productId,String  userId) throws Exception {
        //先封装对象，放入redis队列
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setProductId(productId);
        skillEntity.setUserId(userId);
        redisTemplate.boundListOps(SKILL_GOODS_LIST).leftPush(skillEntity);
        mutilThreadOrder.createOrder();
    }
}
