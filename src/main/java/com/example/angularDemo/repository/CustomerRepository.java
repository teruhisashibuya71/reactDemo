package com.example.angularDemo.repository;

import com.example.angularDemo.model.Customer;
import com.example.angularDemo.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 顧客リポジトリ
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
