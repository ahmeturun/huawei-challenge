package com.example.todo.todo.service;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityNotFoundException;

import com.example.todo.todo.model.Status;
import com.example.todo.todo.model.ToDoItem;
import com.example.todo.todo.model.ToDoList;
import com.example.todo.todo.repository.ToDoListRepository;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ToDoService {

    @Autowired
    private ToDoListRepository toDoListRepository;

    public List<ToDoList> getLists() {
        Iterable<ToDoList> resultIterable = toDoListRepository.findAll();
        return IterableUtils.toList(resultIterable);
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
                List<ToDoItem> toDoItems = item.getDependents();
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