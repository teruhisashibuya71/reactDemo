package com.example.angularDemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.angularDemo.model.TestUser;
import com.example.angularDemo.repository.TestUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestUserServiceImpl implements TestUserService {

    private final TestUserRepository userRepository;


    /**
     * すべてのユーザーを取得するメソッド
     * 
     */
    @Override
    public List<TestUser> getAllUsers() {
        return userRepository.findAll();
    }

}
