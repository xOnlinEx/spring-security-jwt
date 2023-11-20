package com.xonlinex.springsecurityjwt.repository;

import com.xonlinex.springsecurityjwt.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
