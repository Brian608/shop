package org.feather.service;

import org.feather.dao.SkillGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-06 23:49
 **/
@Component
public class SkillGoodsService {
    @Autowired
    private RedisTemplate redisTemplate;

    public static  final String SKILL_GOODS_PHONE="SKILL_GOODS_PHONE";

    @Autowired
    private SkillGoodsRepository skillGoodsRepository;
}
