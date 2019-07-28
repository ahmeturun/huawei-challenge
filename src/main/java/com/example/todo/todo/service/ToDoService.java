package com.example.todo.todo.service;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import com.example.todo.todo.entity.Status;
import com.example.todo.todo.entity.ToDoItem;
import com.example.todo.todo.entity.ToDoList;
import com.example.todo.todo.entity.User;
import com.example.todo.todo.repository.ToDoListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ToDoService {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private UserService userService;

    public List<ToDoList> getLists(String userName) {
        // get user from sessionId
        User currentUser = userService.getUserFromUserName(userName);
        return StreamSupport.stream(toDoListRepository.findAll().spliterator(), false)
            .filter(list -> list.getUserId() == currentUser.getId()).collect(Collectors.toList());
    }

    public void deleteList(Long id) {
        toDoListRepository.deleteById(id);
    }

    public ToDoList saveList(ToDoList toDoList){
        return toDoListRepository.save(toDoList);
    }

    public ToDoList updateList(ToDoList toDoList){

        Long listId = toDoList.getId();
        if(!toDoListRepository.existsById(listId)){
            throw new EntityNotFoundException("List not found");
        }
        ToDoList existingItem = toDoListRepository.findById(listId).orElseThrow();
        existingItem.getToDoItems().forEach(item -> {
            ToDoItem itemOnUpdatedList = toDoList.getToDoItems().stream()
                .filter(el -> el.getId() == item.getId()).findFirst().orElse(null);

            if(itemOnUpdatedList != null 
                && itemOnUpdatedList.getStatus() == Status.Complete
                && item.getStatus() != itemOnUpdatedList.getStatus()){
                //item status is updated, check for dependent items
                Set<ToDoItem> toDoItems = item.getDependents();
                Predicate<ToDoItem> isNotCompletedPredicate = e -> !e.isCompleted();
                boolean foundNotCompleted = toDoItems.stream().anyMatch(isNotCompletedPredicate);
                if(foundNotCompleted){
                    throw new Error("You cannot mark item complete if all dependents are not completed!");
                }
            }
        });
        return toDoListRepository.save(toDoList);
    }
}