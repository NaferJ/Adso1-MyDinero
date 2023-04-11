package com.mydinero.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mydinero.demo.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
}
