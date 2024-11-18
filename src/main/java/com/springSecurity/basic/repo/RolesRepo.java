package com.springSecurity.basic.repo;


import com.springSecurity.basic.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles, Long> {

        Roles findById(long id);
}
