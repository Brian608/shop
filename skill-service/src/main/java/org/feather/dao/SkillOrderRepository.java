package org.feather.dao;

import org.feather.entity.SkillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillOrderRepository extends JpaRepository<SkillOrder,Long> {
}
