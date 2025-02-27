package com.example.Volunteering_Platform.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Volunteering_Platform.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

   @Query("SELECT t FROM Task t WHERE LOWER(t.title) = LOWER(:title)")
    List<Task> findByTitleIgnoreCase(@Param("title") String title);
    
    @Query("SELECT l FROM Task l WHERE LOWER(l.location) = LOWER(:location)")
    List<Task> findByLocationIgnoreCase(@Param("location")String location);
    
    @Query("SELECT c FROM Task c WHERE LOWER(c.category) = LOWER(:category)")
    List<Task> findByCategoryIgnoreCase(@Param("category")String category);

    List<Task> findByEventDate(LocalDate eventDate);
}
