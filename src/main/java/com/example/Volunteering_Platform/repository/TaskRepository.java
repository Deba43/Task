package com.example.Volunteering_Platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Volunteering_Platform.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTitle(String title);
    Optional<Task> findByLocation(String location);
    Optional<Task> findByCategory(String category);
}
