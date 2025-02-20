package com.example.Volunteering_Platform.controller;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Volunteering_Platform.model.Organization;
import com.example.Volunteering_Platform.model.Task;
import com.example.Volunteering_Platform.service.OrganizationService;
import com.example.Volunteering_Platform.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/addTaskOrganization/{organizationId}")
    public ResponseEntity<Task> addTaskOrganization(
            @PathVariable Long organizationId,
            @RequestBody Task task) {

        Optional<Organization> org = organizationService.findOrganizationById(organizationId);
        if (org.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (task.getEndAt() != null && task.getEventDate() != null) {
            LocalDateTime eventDateTime = task.getEventDate().atStartOfDay();

            if (task.getEndAt().isBefore(eventDateTime)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (task.getEndAt().isBefore(eventDateTime)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }

        try {
            Organization organization = org.get();
            task.setOrg(organization);

            Task savedTask = taskService.saveTask(task);
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllTasks")
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

}
