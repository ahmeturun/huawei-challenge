package com.example.todo.todo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

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

    @Column(unique=true)
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

    @JsonProperty("password")
    private String password;
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("sessionId")
    private String sessionID;
    /**
     * @return the sessionID
     */
    public String getSessionID() {
        return sessionID;
    }
    /**
     * @param sessionID the sessionID to set
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    @JsonProperty("lastLogin")
    private Long lastLogin;
    /**
     * @return the lastLogin
     */
    public Long getLastLogin() {
        return lastLogin;
    }
    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public User(){

    }

    public User(Long id, String name, String password, String sessionID, Long lastLogin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sessionID = sessionID;
        this.lastLogin = lastLogin;
    }    

}