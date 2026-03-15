package com.habibullahdm.auth.repository;

import com.habibullahdm.auth.model.entity.UserRole;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends BaseJpaRepository<UserRole, String> {
}
