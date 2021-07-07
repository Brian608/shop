package org.feather.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: EShopParent
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2021-07-06 23:22
 **/
@Entity
@Table(name = "skill_goods")
public class SkillGoods implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 标题
     */
    @Column(name = "name")
    private String name;

    /**
     * 原价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 秒杀价格
     */
    @Column(name = "cost_price")
    private BigDecimal costPrice;

    /**
     * 审核状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 数量
     */
    @Column(name = "num")
    private  Integer num;

    /**
     * 剩余库存
     */
    @Column(name = "stock_count")
    private  Integer stockCount;

    /**
     * 介绍
     */
    @Column(name = "introduction")
    private String introduction;

    public SkillGoods() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
