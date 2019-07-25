package com.example.todo.todo;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

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

    @Test
    public void updateListMustReturnUpdatedObject() {
        ToDoItem item1 = new ToDoItem("innerItem", "innerdesc", "1563996931", Status.NotComplete);
        ToDoItem item2 = new ToDoItem("dependencyItem", "item with dependency", "1563996931", Status.NotComplete);
        ToDoList toDoList = new ToDoList("testList", Long.valueOf(1), item1, item2);

        ToDoList savedList = toDoService.saveList(toDoList);

        Predicate<ToDoItem> itemPredicate = e -> e.getName().equals("innerItem");
        ToDoItem item = savedList.getToDoItems().stream().filter(itemPredicate).findFirst().get();
        Set<ToDoItem> newItems = new HashSet<ToDoItem>();
        newItems.add(item);
        savedList.setToDoItems(newItems);
        Integer newToDoItemsSize = newItems.size();

        ToDoList updatedList = toDoService.updateList(savedList);

        Assert.assertNotNull("updated list can't be null.", updatedList);
        Assert.assertTrue(updatedList.getToDoItems().size() == newToDoItemsSize);
    }

    @Test
    public void updateListWithIncompleteDependentMustFail(){
        ToDoItem item1 = new ToDoItem("innerItem", "innerdesc", "1563996931", Status.NotComplete);
        ToDoItem item2 = new ToDoItem("dependencyItem", "item with dependency", "1563996931", Status.NotComplete, item1);
        ToDoList toDoList = new ToDoList("testList", Long.valueOf(1), item1, item2);

        ToDoList savedList = toDoService.saveList(toDoList);

        Predicate<ToDoItem> isNotCompletedPredicate = e -> e.getName().equals("dependencyItem");
        ToDoItem dependentItem = savedList.getToDoItems().stream().filter(isNotCompletedPredicate).findFirst().get();
        dependentItem.setStatus(Status.Complete);
        boolean result = false;
        try {
            toDoService.updateList(savedList);
        } catch (Error e) {
            if(e.getMessage().contains("You cannot mark item complete")){
                result = true;
            }
        }
        assertTrue("update should've failed", result);
    }
}