package uiai.task;

import java.util.Date;

public class Task {
    public String description;
    protected boolean isDone;
    public String toDate;
    public String fromDate;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[ ]" + "[" + getStatusIcon() + "] " + description;
    }

}