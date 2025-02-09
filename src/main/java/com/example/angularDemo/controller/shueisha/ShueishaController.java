package com.example.angularDemo.controller.shueisha;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shueisya")
public class ShueishaController {
    

    /**
     * 集英社のURLを姿勢するためのテストページを表示します
     * 
     * http://localhost:8080/shueisya
     * 
     * @param model
     * @return
     */
    @GetMapping
    public String showHome(Model model){
        
        // 集英社のリンクを生成するために必要な2つの条件をmodelにセットする
        model.addAttribute("hasGuest", Boolean.FALSE);
        model.addAttribute("isRimacomi", Boolean.TRUE);

        // 集英社のテストページを返却
        return "shueisya/test";
    }
}
