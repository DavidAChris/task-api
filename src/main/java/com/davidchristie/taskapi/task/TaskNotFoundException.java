package com.davidchristie.taskapi.task;

public class TaskNotFoundException extends RuntimeException {
    TaskNotFoundException(long taskId) {
        super("Unable to find task " + taskId);
    }
}
