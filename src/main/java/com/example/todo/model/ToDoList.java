package com.example.todo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer userId;
    private List<ToDoItem> toDoItems;

    protected ToDoList() {}

    public ToDoList(String name, Integer userId){
        this.name = name;
        this.userId = userId;
    }
}