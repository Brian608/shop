package org.feather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-04 09:45
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class);
    }
}
