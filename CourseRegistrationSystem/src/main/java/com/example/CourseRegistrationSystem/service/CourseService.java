package com.example.CourseRegistrationSystem.service;

import com.example.CourseRegistrationSystem.model.Course;
import com.example.CourseRegistrationSystem.model.CourseRegistery;
import com.example.CourseRegistrationSystem.repository.CourseRegisteryRepo;
import com.example.CourseRegistrationSystem.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public class CourseService {

     @Autowired
     CourseRepo courseRepo;

     @Autowired
     CourseRegisteryRepo courseRegisteryRepo;

     public List<Course> avaliableCourses() {
         return courseRepo.findAll();
     }

    public List<CourseRegistery> enrolledStudents() {
         return courseRegisteryRepo.findAll();
    }

    public void enrollCourse(String name, String emailId, String courseName) {
         CourseRegistery courseRegistery = new CourseRegistery(name, emailId, courseName);
         courseRegisteryRepo.save(courseRegistery);
    }
}
