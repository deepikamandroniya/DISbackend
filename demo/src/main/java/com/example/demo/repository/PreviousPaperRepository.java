package com.example.demo.repository;

import com.example.demo.model.PreviousPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreviousPaperRepository extends JpaRepository<PreviousPaper, Long> {
}
