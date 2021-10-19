package com.example.demo.repo;

import com.example.demo.model.SomeEntity;
import com.example.demo.model.SubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubEntityRepo extends JpaRepository<SubEntity, Long> {
}
