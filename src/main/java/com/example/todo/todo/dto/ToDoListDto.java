package com.example.todo.todo.dto;

import java.util.Set;

import com.example.todo.todo.entity.ToDoItem;

public class ToDoListDto {

    
    
    private Long id;
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    private String name;
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    private Long userId;
    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    private Set<ToDoItem> toDoItems;
    /**
     * @param toDoItems the toDoItems to set
     */
    public void setToDoItems(Set<ToDoItem> toDoItems) {
        this.toDoItems = toDoItems;
    }
    /**
     * @return the toDoItems
     */
    public Set<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    public ToDoListDto() {

    }

    public ToDoListDto(Long id, String name, Long userId, Set<ToDoItem> toDoItems) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.toDoItems = toDoItems;
    }
}