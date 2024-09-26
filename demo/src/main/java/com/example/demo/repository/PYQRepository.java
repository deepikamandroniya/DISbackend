package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PYQ;

@Repository
public interface PYQRepository extends JpaRepository<PYQ, Long> {

    // Custom query method to find by semester, type, and course
    Optional<PYQ> findBySemesterAndTypeAndCourse(String semester, String type, String course);
}
