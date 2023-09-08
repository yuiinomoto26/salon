package com.example.salon.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.salon.entity.Menu;

public interface MenuRepository extends CrudRepository<Menu, String>{
	
}
