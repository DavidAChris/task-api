package com.davidchristie.taskapi.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final AtomicLong counter = new AtomicLong();
    /*
     * Saves Tasks in a key-value pair structure, we can partition the hashmap of tasks on id since they are all
     * unique, allowing us to quickly access a task when it is requested
     */
    private HashMap<Long, Task> tasks = new HashMap<>();

    public void createTask(Task newTask) {
        newTask.setId(counter.incrementAndGet());
        tasks.put(newTask.getId(), newTask);
    }

    public ArrayList<Task> getTasks() {
        Collection<Task> values = tasks.values();
        return new ArrayList<>(values);
    }

    public Task getTaskById(long taskId) throws TaskNotFoundException {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new TaskNotFoundException(taskId);
        }
        return task;
    }

    public void updateTaskById(long taskId, String title, String description, Boolean completed) throws TaskNotFoundException {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new TaskNotFoundException(taskId);
        }
        if (title != null && !title.equals(task.getTitle()) && !title.isEmpty()) {
            task.setTitle(title);
        }
        if (description != null && !description.equals(task.getDescription()) && !description.isEmpty()) {
            task.setDescription(description);
        }
        if (completed != null && completed != task.isCompleted()) {
            task.updateCompleted(completed);
        }
    }
}
