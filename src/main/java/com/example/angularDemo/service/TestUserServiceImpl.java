package com.example.angularDemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.angularDemo.controller.form.UserForm;
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

    /**
     * ユーザーの新規作成
     * 
     * @param testUser
     * @return
     */
    @Override
    public TestUser createUser(UserForm form) {

        TestUser user = new TestUser();
        user.setName(form.getName());
        user.setAge(form.getAge());
        return userRepository.save(user);
    }

    /**
     * 削除処理
     */
    @Override
    public void deleteUser(Long id){    
        userRepository.deleteById(id);
    }
}
