package com.example.todo.repository;

import java.util.List;

import com.example.todo.model.ToDoList;

import org.springframework.data.repository.CrudRepository;

public interface ToDoListRepository extends CrudRepository<ToDoList, Long>{

    // List<ToDoList> GetToDoListForUser(String user);
    // ToDoList AddToDoList(ToDoList toDoList);
    // boolean DeleteToDoList(Integer toDoList);
    // ToDoList UpdateToDoList(ToDoList toDoList);
}