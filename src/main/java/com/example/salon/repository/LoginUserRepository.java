package com.example.salon.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.salon.entity.LoginUser;

public interface LoginUserRepository extends CrudRepository<LoginUser, String>{

}
