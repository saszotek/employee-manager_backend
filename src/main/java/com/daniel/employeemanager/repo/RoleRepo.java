package com.daniel.employeemanager.repo;

import com.daniel.employeemanager.model.ERole;
import com.daniel.employeemanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
