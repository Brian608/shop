package org.feather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-06 23:05
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
public class SkillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkillApplication.class);
    }
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> template=new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //采用普通的key 字符串
        template.setStringSerializer(new StringRedisSerializer());
        return  template;
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
