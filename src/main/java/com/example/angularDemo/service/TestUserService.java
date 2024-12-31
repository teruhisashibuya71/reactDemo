package com.example.angularDemo.service;

import java.util.List;

import com.example.angularDemo.controller.form.UserForm;
import com.example.angularDemo.model.TestUser;

/**
 * サービス インターフェース
 * 詳細は実装クラスを参照のこと
 * 
 */
public interface TestUserService {
    
    public List<TestUser> getAllUsers();

    public TestUser createUser(UserForm user);

    public void deleteUser(Long id);


    // public User getUserById(Long id);


    // public User updateUser(Long id, User newUser);

    
}
