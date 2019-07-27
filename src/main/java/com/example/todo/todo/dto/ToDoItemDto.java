package com.example.todo.todo.dto;

import java.util.Set;

import com.example.todo.todo.entity.Status;
import com.example.todo.todo.entity.ToDoItem;
import com.example.todo.todo.entity.ToDoList;

public class ToDoItemDto {

    
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

    private ToDoList toDoList;
    /**
     * @param toDoList the toDoList to set
     */
    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }
    /**
     * @return the toDoList
     */
    public ToDoList getToDoList() {
        return toDoList;
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

    private String description;
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    private String deadline;
    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    /**
     * @return the deadline
     */
    public String getDeadline() {
        return deadline;
    }

    private Status status;
    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    private Set<ToDoItem> dependents;
    /**
     * @param dependents the dependents to set
     */
    public void setDependents(Set<ToDoItem> dependents) {
        this.dependents = dependents;
    }
    /**
     * @return the dependents
     */
    public Set<ToDoItem> getDependents() {
        return dependents;
    }

    public ToDoItemDto() {

    }

    public ToDoItemDto(Long id, String name, String description, String deadline, Status status,
            Set<ToDoItem> dependents) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.dependents = dependents;
    }

}