package com.example.Volunteering_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Volunteering_Platform.model.Task;
import com.example.Volunteering_Platform.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }
    public Optional<Task> getTaskByName(String title) {
        return taskRepository.findByTitle(title);
    }
    
    public Optional<Task> getTaskByLocation(String location) {
        return taskRepository.findByLocation(location);
    }
    public Optional<Task> getTaskByCategory(String category) {
        return taskRepository.findByCategory(category);
    }
    
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

}
