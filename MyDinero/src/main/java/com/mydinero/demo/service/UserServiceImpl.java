package com.mydinero.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydinero.demo.entity.User;
import com.mydinero.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repository;
	
	@Override
	public Iterable<User> getAllUsers(){
		return repository.findAll();
	}
}
