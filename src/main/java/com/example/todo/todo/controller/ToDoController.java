package com.example.todo.todo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.todo.todo.dto.ToDoListDto;
import com.example.todo.todo.entity.ToDoList;
import com.example.todo.todo.entity.User;
import com.example.todo.todo.service.ToDoService;
import com.example.todo.todo.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET, path = "/GetToDoList")
    public List<ToDoListDto> getToDoLists(String userName) {
        List<ToDoList> toDoLists = toDoService.getLists(userName);
        return toDoLists.stream().map(toDoList -> modelMapper.map(toDoList, ToDoListDto.class)).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/DeleteList")
    public void deleteList(Long id) {
        toDoService.deleteList(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/SaveList")
    public ToDoListDto saveList(@RequestHeader("sessionId") String sessionId, @RequestBody ToDoListDto toDoList) {
        User currentUser = userService.getUserFromSessionId(sessionId);
        toDoList.setUserId(currentUser.getId());
        ToDoList fromDto = modelMapper.map(toDoList, ToDoList.class);
        ToDoList savedList = toDoService.saveList(fromDto);
        return modelMapper.map(savedList, ToDoListDto.class);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/UpdateList")
    public ToDoListDto updateList(@RequestBody ToDoListDto toDoList) {
        ToDoList updatedList = toDoService.updateList(modelMapper.map(toDoList, ToDoList.class));
        return modelMapper.map(updatedList, ToDoListDto.class);
    }
}