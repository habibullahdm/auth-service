package com.habibullahdm.auth.repository;

import com.habibullahdm.auth.model.entity.Role;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseJpaRepository<Role, String> {
    List<Role> findAll();
}
