package com.example.angularDemo.controller.enums;

import com.example.angularDemo.enums.CategoryEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/enums")
public class EnumsController {


    /**
     * http://localhost:8080/enums/all
     * enumのテスト
     *
     * @param model
     * @return
     */
    @GetMapping("/all")
    public String showCategoryEnumAll(Model model){

        // enumすべてを取得
        List<CategoryEnum> categoryEnumList = CategoryEnum.getAllValues();
        model.addAttribute("categoryEnumList", categoryEnumList);

        // viewを表示する場合は最初のスラッシュ必要ない
        return "enums/enums-test";
    }
}
