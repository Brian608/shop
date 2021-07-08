package org.feather.service;

import org.feather.async.MutilThreadOrder;
import org.feather.dao.SkillOrderRepository;
import org.feather.entity.SkillEntity;
import org.feather.entity.SkillGoods;
import org.feather.entity.SkillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.Date;
import java.util.UUID;

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

    public  static  final String SKILL_GOODS_ONLY="SKILL_GOODS_ONLY";
//库存队列
    public static  final String SKILL_GOODS_QUEUE="SKILL_GOODS_QUEUE";
    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private MutilThreadOrder mutilThreadOrder;
    
    @Autowired
    private ProductService productService;

    @Transactional
    public  void  add(Long productId,String  userId) throws Exception {
        //测试超卖问题，将userId随时更新
        userId= UUID.randomUUID().toString();
        //判断用户是否参加过抢单
        Long increment = redisTemplate.boundHashOps(SKILL_GOODS_ONLY).increment(userId, 1L);
        if (increment>1){
            throw  new Exception("重复抢单");
        }
        //先封装对象，放入redis队列
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setProductId(productId);
        skillEntity.setUserId(userId);
        redisTemplate.boundListOps(SKILL_GOODS_LIST).leftPush(skillEntity);
        mutilThreadOrder.createOrder();
    }
}
