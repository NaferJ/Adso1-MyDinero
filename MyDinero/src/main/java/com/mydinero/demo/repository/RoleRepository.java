package com.mydinero.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mydinero.demo.entity.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
