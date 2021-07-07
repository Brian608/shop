package org.feather.service;

import org.feather.dao.SkillGoodsRepository;
import org.feather.entity.SkillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    /**
     * 每5秒执行一次，将需要秒杀的商品列表加载到内村
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public  void prepareGoods(){
        System.out.println("开始加载商品");
        //获取所有已经在内存中的商品列表ID
        Set<Long> set = redisTemplate.boundHashOps(SKILL_GOODS_PHONE).keys();
        List<Long> ids =new ArrayList<>();
        for (Long id:set) {
            ids.add(id);
        }
        List<SkillGoods> list=null;
        //只查询不存在内存当中的商品信息，并加载到内存
        if (CollectionUtils.isEmpty(ids)){
            list=skillGoodsRepository.findSkillAll();
        }else {
            list=skillGoodsRepository.findSkillGoods(ids);
        }
        if (!CollectionUtils.isEmpty(list)){
            for (SkillGoods skillGood:
                 list) {
                redisTemplate.boundHashOps(SKILL_GOODS_PHONE).put(skillGood.getId(),skillGood);
            }
        }
        //查询当前缓存中所有的商品信息
        Set keys = redisTemplate.boundHashOps(SKILL_GOODS_PHONE).keys();
        for (Object s:keys
             ) {
           SkillGoods skillGood= (SkillGoods)redisTemplate.boundHashOps(SKILL_GOODS_PHONE).get(s);
            System.out.println(skillGood.getName()+"库存剩余"+skillGood.getStockCount());
        }
    }
    //提供商品信息的方法
    public  SkillGoods queryProduct(Long productId){
        return (SkillGoods) redisTemplate.boundHashOps(SKILL_GOODS_PHONE).get(productId);
    }
    //更新商品信息
    public  void  updateProduct(SkillGoods skillGoods){
        skillGoodsRepository.save(skillGoods);
    }
}
