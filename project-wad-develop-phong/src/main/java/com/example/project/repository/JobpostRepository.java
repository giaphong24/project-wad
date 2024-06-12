package com.example.project.repository;

import com.example.project.model.Jobpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface JobpostRepository extends JpaRepository<Jobpost, Long> {
}
