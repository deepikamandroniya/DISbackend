package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.model.PYQ;
import com.example.demo.repository.PYQRepository;

@Service
public class PYQService {

    @Autowired
    private PYQRepository pyqRepository;

    // Method to find PYQ by semester, type, and course
    public Optional<PYQ> findBySemesterTypeAndCourse(String semester, String type, String course) {
        return pyqRepository.findBySemesterAndTypeAndCourse(semester, type, course);
    }
}
