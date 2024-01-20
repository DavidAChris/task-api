package com.davidchristie.taskapi.task;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
    TaskService taskService = new TaskService();

    @PostMapping
    public void createTask(@RequestBody Task newTask) {
        taskService.createTask(newTask);
    }

    @GetMapping
    public ArrayList<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(path = "{taskId}")
    public Task getTaskById(@PathVariable("taskId") long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PutMapping(path = "{taskId}")
    public void updateTaskById(
            @PathVariable("taskId") long taskId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) boolean completed) {
        taskService.updateTaskById(taskId, title, description, completed);
    }
}
