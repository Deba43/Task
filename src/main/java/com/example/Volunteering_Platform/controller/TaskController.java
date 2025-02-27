package com.example.Volunteering_Platform.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Volunteering_Platform.model.Task;
import com.example.Volunteering_Platform.service.TaskService;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        if (task.getEndDate() != null && task.getEventDate() != null) {
            if (task.getEndDate().isBefore(task.getEventDate())) { // Compare directly
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        try {
            Task savedTask = taskService.saveTask(task);
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/viewAllTasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/getTaskById/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId).orElse(null);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTaskByName/{title}")
    public ResponseEntity<List<Task>> getTaskByName(@PathVariable String title) {
        List<Task> tasks = taskService.getTaskByName(title);
        if (tasks != null) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTaskByLocation/{location}")
    public ResponseEntity<List<Task>> getTaskByLocation(@PathVariable String location) {
        List<Task> tasks = taskService.getTaskByLocation(location);
        if (tasks != null) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTaskByCategory/{category}")
    public ResponseEntity<List<Task>> getTaskByCategory(@PathVariable String category) {
        List<Task> tasks = taskService.getTaskByCategory(category);
        if (tasks != null) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTaskByDate/{eventDate}")
    public ResponseEntity<List<Task>> getTaskByDate(@PathVariable LocalDate eventDate) {
        List<Task> tasks = taskService.getTaskByDate(eventDate);
        if (tasks != null) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
