package com.example.todo.todo.repository;

import com.example.todo.todo.entity.ToDoList;

import org.springframework.data.repository.CrudRepository;

public interface ToDoListRepository extends CrudRepository<ToDoList, Long>{

}