package com.example.demo.repo;

import com.example.demo.model.SomeEntity;
import com.example.demo.model.SubEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubEntityRepo extends JpaRepository<SubEntity, Long> {

    @Query("select se from SubEntity se join fetch se.entity")
    List<SubEntity> findPage(Pageable page);
}
