package com.example.todo.todo.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    public Long getId(){
        return id;
    } 
    private String name;
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Set<ToDoItem> toDoItems;
    public Set<ToDoItem> getToDoItems(){
        return toDoItems;
    }

    protected ToDoList(){
        
    }

    public ToDoList(String name, Long userId, ToDoItem... toDoItems){
        this.name = name;
        this.userId = userId;
        this.toDoItems = Stream.of(toDoItems).collect(Collectors.toSet());
        this.toDoItems.forEach(el -> el.setToDoList(this));
    }
}