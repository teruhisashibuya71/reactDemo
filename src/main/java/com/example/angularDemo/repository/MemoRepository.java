package com.example.angularDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.angularDemo.model.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    
}
