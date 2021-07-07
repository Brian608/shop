package org.feather.entity;

import java.io.Serializable;

/**
 * @program: EShopParent
 * @description:用户排队抢单信息
 * @author: 杜雪松(feather)
 * @create: 2021-07-07 21:47
 **/
public class SkillEntity implements Serializable {
    private  Long productId;

    private  String userId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
