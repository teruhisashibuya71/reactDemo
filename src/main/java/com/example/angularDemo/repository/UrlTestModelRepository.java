package com.example.angularDemo.repository;

import com.example.angularDemo.model.TestUser;
import com.example.angularDemo.model.UrlTestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlTestModelRepository extends JpaRepository<UrlTestModel, Long> {
}
