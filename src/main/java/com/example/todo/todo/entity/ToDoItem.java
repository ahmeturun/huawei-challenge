package com.example.todo.todo.entity;

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
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    @JsonIgnore
    @ManyToOne
    private ToDoList toDoList;
    /**
     * @return the toDoList
     */
    public ToDoList getToDoList() {
        return toDoList;
    }
    public void setToDoList(ToDoList toDoList){
        this.toDoList = toDoList;
    }
    @JsonProperty("name")
    private String name;
    public String getName(){
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("description")
    private String description;
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    @JsonProperty("deadline")
    private String deadline;
    /**
     * @return the deadline
     */
    public String getDeadline() {
        return deadline;
    }
    /**
    * @param deadline the deadline to set
    */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    @JsonProperty("status")
    private Status status;
    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    @JsonProperty("dependents")
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<ToDoItem> dependents = new HashSet<ToDoItem>();
    /**
     * @return the dependents
     */
    public Set<ToDoItem> getDependents() {
        return dependents;
    }
    /**
     * @param dependents the dependents to set
     */
    public void setDependents(Set<ToDoItem> dependents) {
        this.dependents = dependents;
    }
    public void addDependent(ToDoItem toDoItem){
        dependents.add(toDoItem);
    }

    public ToDoItem(){
        
    }

    

    // public ToDoItem(String name,
    //     String description,
    //     String deadline,
    //     Status status,
    //     ToDoItem... toDoItems){
    //         this.name = name;
    //         this.description = description;
    //         this.deadline = deadline;
    //         this.status = status;
    //         this.dependents = Stream.of(toDoItems).collect(Collectors.toSet());
    // }

    public boolean isCompleted(){
        return status == Status.Complete;
    }

    public ToDoItem(Long id, String name, String description, String deadline, Status status,
            Set<ToDoItem> dependents) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.dependents = dependents;
    }
}