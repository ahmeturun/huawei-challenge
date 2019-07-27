package com.example.todo.todo.dto;

public class UserDto {
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

    private String sessionId;
    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }
    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private String lastLogin;
    /**
     * @return the lastLogin
     */
    public String getLastLogin() {
        return lastLogin;
    }
    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UserDto() {

    }

    public UserDto(Long id, String name, String password, String sessionId, String lastLogin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sessionId = sessionId;
        this.lastLogin = lastLogin;
    }
}