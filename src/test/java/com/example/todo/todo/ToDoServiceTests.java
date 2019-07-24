package com.example.todo.todo;

import java.util.List;

import com.example.todo.todo.model.Status;
import com.example.todo.todo.model.ToDoItem;
import com.example.todo.todo.model.ToDoList;
import com.example.todo.todo.service.ToDoService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ToDoServiceTests {

    @Autowired
    private ToDoService toDoService;

    @Test
    public void getListMustReturnAllRecords() {
        List<ToDoList> toDoList = toDoService.getLists();
        Assert.assertNotNull("getlist shouldn't be null", toDoList);
        Assert.assertTrue("List should contain initial elements", toDoList.size() > 0);
    }

    @Test
    public void saveListMustReturnSavedObjectWithId() {
        ToDoItem item1 = new ToDoItem("innerItem", "innerdesc", "1563996931", Status.NotComplete);
        ToDoItem item2 = new ToDoItem("dependencyItem", "item with dependency", "1563996931", Status.NotComplete);
        ToDoList toDoList = new ToDoList("testList", Long.valueOf(1), item1, item2);

        ToDoList savedList = toDoService.saveList(toDoList);

        Assert.assertNotNull("saved item shouldn't be null.", savedList);
        Assert.assertTrue("saved list should have assigned an id.", savedList.getId() > 0);
    }

    // @Test
    // public void updateListWithDependent
}