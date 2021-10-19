package com.example.demo.repo;

import com.example.demo.model.SomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SomeEntityRepo extends JpaRepository<SomeEntity, Long> {
}
