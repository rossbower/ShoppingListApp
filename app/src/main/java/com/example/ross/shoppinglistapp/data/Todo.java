package com.example.ross.shoppinglistapp.data;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Todo extends SugarRecord implements Serializable{

    private String todoTitle;
    private String todoDescription;
    private String todoPrice;
    private String todoType;
    private boolean done;

    public Todo(){

    }

    public Todo(String todoTitle, String todoDescription, String todoPrice, String todoType, boolean done) {
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.todoPrice = todoPrice;
        this.done = done;
        this.todoType = todoType;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public String getTodoPrice() {
        return todoPrice;
    }

    public void setTodoPrice(String todoPrice) {
        this.todoPrice = todoPrice;
    }

    public String getTodoType() {
        return todoType;
    }

    public void setTodoType(String todoType) {
        this.todoType = todoType;
    }
}
