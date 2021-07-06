package org.feather.dao;

import org.feather.entity.SkillGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillGoodsRepository extends JpaRepository<SkillGoods,Long> {

    @Query(value = "SELECT * FROM skill_goods WHERE STATUS= 1 AND num >0 AND stock_count>0 and id not in(?1)",nativeQuery = true)
    List<SkillGoods>  findSkillGoods(List<Long> ids);

    @Query(value = "SELECT * FROM skill_goods WHERE STATUS= 1 AND num >0 AND stock_count>0",nativeQuery = true)
    List<SkillGoods> findSkillAll();

}
