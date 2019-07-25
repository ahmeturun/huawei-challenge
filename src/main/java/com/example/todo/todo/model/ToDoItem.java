package com.example.todo.todo.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ToDoItem {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    public Long getId(){
        return id;
    }
    @JsonIgnore
    @ManyToOne
    private ToDoList toDoList;
    public void setToDoList(ToDoList toDoList){
        this.toDoList = toDoList;
    }
    @JsonProperty("name")
    private String name;
    public String getName(){
        return name;
    }
    @JsonProperty("description")
    private String description;
    @JsonProperty("deadline")
    private String deadline;
    @JsonProperty("status")
    private Status status;
    public Status getStatus(){
        return status;
    }
     /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    };
    @JsonProperty("dependents")
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<ToDoItem> dependents = new HashSet<ToDoItem>();
    public Set<ToDoItem> getDependents(){
        return dependents;
    };
    public void addDependent(ToDoItem toDoItem){
        dependents.add(toDoItem);
    }

    protected ToDoItem(){
        
    }

    public ToDoItem(String name,
        String description,
        String deadline,
        Status status,
        ToDoItem... toDoItems){
            this.name = name;
            this.description = description;
            this.deadline = deadline;
            this.status = status;
            this.dependents = Stream.of(toDoItems).collect(Collectors.toSet());
    }

    public boolean isCompleted(){
        return status == Status.Complete;
    }
}