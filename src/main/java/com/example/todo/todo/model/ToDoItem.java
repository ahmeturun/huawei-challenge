package com.example.todo.todo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ToDoItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    public Long getId(){
        return id;
    }
    @ManyToOne
    private ToDoList toDoList;
    public void setToDoList(ToDoList toDoList){
        this.toDoList = toDoList;
    }
    private String name;
    private String description;
    private String deadline;
    private Status status;
    public Status getStatus(){
        return status;
    }
    @OneToMany(targetEntity = ToDoItem.class,cascade = CascadeType.ALL)
    private List<ToDoItem> dependents;
    public List<ToDoItem> getDependents(){
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
        Status status){
            this.name = name;
            this.description = description;
            this.deadline = deadline;
            this.status = status;
    }

    public boolean isCompleted(){
        return status == Status.Complete;
    }
}