package com.example.angularDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.angularDemo.model.TestUser;

/**
 * ユーザー レポジトリ
 * テスト用のレポジトリです
 * 基本的に、Repositoryはテスト用にも使い回します。
 */
@Repository
public interface TestUserRepository extends JpaRepository<TestUser, Long> {
    
}
