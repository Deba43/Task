package com.example.Volunteering_Platform.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Volunteering_Platform.dto.TaskDto;
import com.example.Volunteering_Platform.enums.Status;
import com.example.Volunteering_Platform.exception.InvalidEntityException;
import com.example.Volunteering_Platform.model.Task;
import com.example.Volunteering_Platform.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new InvalidEntityException("Task with ID " + taskId + " not found."));
    }

    public List<Task> getTaskByName(String title) {
        List<Task> tasks = taskRepository.findByTitleIgnoreCase(title);
        if (tasks.isEmpty()) {
            throw new InvalidEntityException("No tasks found with title: " + title);
        }
        return tasks;
    }

    public List<Task> getTaskByLocation(String location) {
        List<Task> tasks = taskRepository.findByLocationIgnoreCase(location);
        if (tasks.isEmpty()) {
            throw new InvalidEntityException("No tasks found in location: " + location);
        }
        return tasks;
    }

    public List<Task> getTaskByCategory(String category) {
        List<Task> tasks = taskRepository.findByCategoryIgnoreCase(category);
        if (tasks.isEmpty()) {
            throw new InvalidEntityException("No tasks found in category: " + category);
        }
        return tasks;
    }

    public List<Task> getTaskByDate(LocalDate eventDate) {
        List<Task> tasks = taskRepository.findByEventDate(eventDate);
        if (tasks.isEmpty()) {
            throw new InvalidEntityException("No tasks found on date: " + eventDate);
        }
        return tasks;
    }

    public Task saveTask(TaskDto taskDto) {
        if (taskDto.getTitle() == null || taskDto.getTitle().isEmpty()) {
            throw new InvalidEntityException("Title cannot be blank");
        }
        if (taskDto.getDescription() == null || taskDto.getDescription().isEmpty()) {
            throw new InvalidEntityException("Description cannot be blank");
        }
        if (taskDto.getLocation() == null || taskDto.getLocation().isEmpty()) {
            throw new InvalidEntityException("Location cannot be blank");
        }
        if (taskDto.getPriority() == null) {
            throw new InvalidEntityException("Priority must be set");
        }
        if (taskDto.getCategory() == null) {
            throw new InvalidEntityException("Category cannot be blank");
        }
        if (taskDto.getEventDate() == null) {
            throw new InvalidEntityException("Event date is required");
        }

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setLocation(taskDto.getLocation());
        task.setPriority(taskDto.getPriority());
        task.setCategory(taskDto.getCategory());
        task.setEventDate(taskDto.getEventDate());
        task.setEndDate(taskDto.getEndDate());

        if (taskDto.getStatus() == null) {
            task.setStatus(Status.PENDING);
        } else {
            task.setStatus(taskDto.getStatus());
        }

        return taskRepository.save(task);
    }

    public List<Task> searchTasks(String title, String location, String category, LocalDate eventDate) {
        List<Task> tasks = taskRepository.findTasksByFilters(title, location, category, eventDate);
        if (tasks.isEmpty()) {
            throw new InvalidEntityException("No tasks match the given search criteria.");
        }
        return tasks;
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        Task task = getTaskById(taskId);

        if (updatedTask.getTitle() == null || updatedTask.getTitle().isEmpty()) {
            throw new InvalidEntityException("Title cannot be blank");
        }
        if (updatedTask.getDescription() == null || updatedTask.getDescription().isEmpty()) {
            throw new InvalidEntityException("Description cannot be blank");
        }
        if (updatedTask.getLocation() == null || updatedTask.getLocation().isEmpty()) {
            throw new InvalidEntityException("Location cannot be blank");
        }
        if (updatedTask.getPriority() == null) {
            throw new InvalidEntityException("Priority must be set");
        }
        if (updatedTask.getCategory() == null) {
            throw new InvalidEntityException("Category cannot be blank");
        }
        if (updatedTask.getEventDate() == null) {
            throw new InvalidEntityException("Event date is required");
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setLocation(updatedTask.getLocation());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setCategory(updatedTask.getCategory());
        task.setEventDate(updatedTask.getEventDate());
        task.setEndDate(updatedTask.getEndDate());

        return taskRepository.save(task);
    }

}
