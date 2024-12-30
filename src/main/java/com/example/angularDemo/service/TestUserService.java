package com.example.angularDemo.service;

import java.util.List;
import com.example.angularDemo.model.TestUser;

/**
 * サービス インターフェース
 * 詳細は実装クラスを参照のこと
 * 
 */
public interface TestUserService {
    
    public List<TestUser> getAllUsers();

    // public User getUserById(Long id);

    // public User createUser(User user);

    // public User updateUser(Long id, User newUser);

    // public User delteUser(Long id);
}
