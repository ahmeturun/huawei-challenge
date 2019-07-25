package com.example.todo.todo.controller;

import java.util.List;

import com.example.todo.todo.model.ToDoList;
import com.example.todo.todo.service.ToDoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @RequestMapping(method = RequestMethod.GET, path = "/GetToDoList")
    public List<ToDoList> getToDoLists() {
        return toDoService.getLists();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/DeleteList")
    public void deleteList(Long id) {
        toDoService.deleteList(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/SaveList")
    public ToDoList saveList(@RequestBody ToDoList toDoList) {
        return toDoService.saveList(toDoList);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/UpdateList")
    public ToDoList updateList(@RequestBody ToDoList toDoList) {
        return toDoService.updateList(toDoList);
    }
}