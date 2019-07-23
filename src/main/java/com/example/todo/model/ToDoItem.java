package com.example.todo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDoItem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String deadline;
    private Status status;
    private List<ToDoItem> dependents;

    protected ToDoItem() {}

    public ToDoItem(String name,
        String description,
        String deadline,
        Status status){
            this.name = name;
            this.description = description;
            this.deadline = deadline;
            this.status = status;
    }
}