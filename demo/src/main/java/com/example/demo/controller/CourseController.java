package com.example.demo.controller;
import com.example.demo.model.Course;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
   
    }
}


