package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.role.RoleImpl;
import com.simplekitchen.project.dao.entity.role.api.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleImpl, Long> {
    RoleImpl findByName(String name);
}
