package com.example.demo.models;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // This model can be stored in the database
public class ToDo {


    @Id // id for database
    @GeneratedValue // value will be generated
    private int id;

    @NotNull // this field cannot be null
    @Size(min=5, max=50, message="Task must have a total character length between 5 and 50.") //error message
    private String task;

    @NotNull
    private Importance important;

    @NotNull
    private Urgency urgent;

    public ToDo(String task) // constructor when a task is the argument
    {
        this.task=task;
    }

    public ToDo() {} //default constructor

    public int getId() {
        return id;
    }

    public String getTask() { //gets task
        return task;
    }
    public void setTask(String task) { //sets task
        this.task = task;
    }

    public Importance getImportant() {
        return important;
    }
    public void setImportant(Importance important) {
        this.important = important;
    }


    public Urgency getUrgent() {
        return urgent;
    }

    public void setUrgent(Urgency urgent) {
        this.urgent = urgent;
    }
}
