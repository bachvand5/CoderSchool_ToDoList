package com.vanistudio.todolist;

/**
 * Created by thuynh6 on 2/12/2016.
 */
public class ListviewRow {
    String task = null;
    String due = null;
    boolean done = false;

    ListviewRow(String task, String due, boolean done) {
        super();
        this.task = task;
        this.due = due;
        this.done = done;
    }

    public String getTask() {
        return this.task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDue() {
        return this.due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
