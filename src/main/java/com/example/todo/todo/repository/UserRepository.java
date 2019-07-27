package com.example.todo.todo.repository;

import com.example.todo.todo.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

}