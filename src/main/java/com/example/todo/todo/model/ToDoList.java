package com.example.todo.todo.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ToDoList {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    public Long getId(){
        return id;
    } 
    @JsonProperty("name")
    private String name;
    @JsonProperty("userId")
    private Long userId;

    @JsonIgnore
    @JsonProperty("toDoItems")
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn
    private Set<ToDoItem> toDoItems;
    public Set<ToDoItem> getToDoItems(){
        return toDoItems;
    }
    public void setToDoItems(Set<ToDoItem> toDoItems){
        this.toDoItems = toDoItems;
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