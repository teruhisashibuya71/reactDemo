package com.example.angularDemo.controller;

import org.hibernate.type.TrueFalseConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.angularDemo.model.Memo;
import com.example.angularDemo.model.TestUser;
import com.example.angularDemo.service.MemoService;
import com.example.angularDemo.service.TestUserService;
import com.example.angularDemo.utils.SwitchValue;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class TestController {
    
    /**
     * ホーム画面の表示テスト
     * http://localhost:8080/home
     * 
     * @return テンプレート ホーム画面
     */
    @GetMapping("/")
    public String showHome() {

        return "home";
    }
    
}
