package com.habibullahdm.auth.repository;

import com.habibullahdm.auth.model.entity.User;
import com.habibullahdm.auth.model.projection.UserRoleProjection;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseJpaRepository<User, String> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query(value = """
            SELECT 
                u.id as userId,
                u.username as username,
                u.email as email,
                u.is_active as isActive,
                r.name as roleName
            FROM auth.users u
            LEFT JOIN auth.user_roles ur ON ur.user_id = u.id
            LEFT JOIN auth.roles r ON r.id = ur.role_id
            """, nativeQuery = true)
    List<UserRoleProjection> findUsersWithRoles();
}
