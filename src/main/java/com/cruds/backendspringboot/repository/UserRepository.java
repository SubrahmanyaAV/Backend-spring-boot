package com.cruds.backendspringboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cruds.backendspringboot.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findFirstByEmail(String email);

}
