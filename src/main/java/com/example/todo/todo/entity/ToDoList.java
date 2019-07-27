package com.example.todo.todo.entity;

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

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ToDoList {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    @JsonProperty("name")
    private String name;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("userId")
    private Long userId;
    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @JsonProperty("toDoItems")
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn
    private Set<ToDoItem> toDoItems;
    /**
     * @return the toDoItems
     */
    public Set<ToDoItem> getToDoItems() {
        return toDoItems;
    }
    /**
     * @param toDoItems the toDoItems to set
     */
    public void setToDoItems(Set<ToDoItem> toDoItems) {
        this.toDoItems = toDoItems;
    }

    public ToDoList(){
        
    }

    public ToDoList(Long id, String name, Long userId, Set<ToDoItem> toDoItems) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.toDoItems = toDoItems;
    }

    

    // public ToDoList(String name, Long userId, ToDoItem... toDoItems){
    //     this.name = name;
    //     this.userId = userId;
    //     this.toDoItems = Stream.of(toDoItems).collect(Collectors.toSet());
    //     this.toDoItems.forEach(el -> el.setToDoList(this));
    // }
}