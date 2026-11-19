package com.example.CourseRegistrationSystem.repository;

import com.example.CourseRegistrationSystem.model.CourseRegistery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegisteryRepo extends JpaRepository<CourseRegistery, Integer> {
}
