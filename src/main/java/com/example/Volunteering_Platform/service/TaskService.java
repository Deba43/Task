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
    public List<Task> getTaskByName(String title) {
        return taskRepository.findByTitleIgnoreCase(title);
    }
    
    
    public List<Task> getTaskByLocation(String location) {
        return taskRepository.findByLocationIgnoreCase(location);
    }
    public List<Task> getTaskByCategory(String category) {
        return taskRepository.findByCategoryIgnoreCase(category);
    }
    
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

}
