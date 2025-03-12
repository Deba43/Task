package com.example.Volunteering_Platform.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Volunteering_Platform.dto.TaskDto;
import com.example.Volunteering_Platform.model.Task;
import com.example.Volunteering_Platform.service.OrganizationService;
import com.example.Volunteering_Platform.service.TaskService;

import jakarta.validation.Valid;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/Organization/{org_id}/addTask")
    public ResponseEntity<Task> addTask(@PathVariable Long org_id, @Valid @RequestBody TaskDto taskDto) {
        System.out.println("Received Task: " + taskDto);

        boolean organizationExists = organizationService.existsById(org_id);
        if (!organizationExists) {
            System.out.println("Organization with ID " + org_id + " does not exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (taskDto.getEndDate() != null && taskDto.getEventDate() != null) {
            if (taskDto.getEndDate().isBefore(taskDto.getEventDate())) {
                System.out.println("End date is before event date");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        try {
            Task savedTask = taskService.saveTask(org_id, taskDto); // Pass org_id for association
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
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
        Task task = taskService.getTaskById(taskId);
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

    @GetMapping("/searchTasks")
    public ResponseEntity<List<Task>> searchTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate) {

        List<Task> tasks = taskService.searchTasks(title, location, category, eventDate);

        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/Organization/{org_id}/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @PathVariable Long org_id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(taskId, org_id, updatedTask);
        return ResponseEntity.ok(task);
    }

}
