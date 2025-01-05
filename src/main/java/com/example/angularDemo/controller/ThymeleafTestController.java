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

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * thymeleafのテスト用コントローラ
 */
@Controller
@RequestMapping("/thymeleaftest")
@RequiredArgsConstructor
public class ThymeleafTestController {

    private final MemoService memoService;
    private final TestUserService userService;



    /**
     * if文章の練習
     * http://localhost:8080/thymeleaftest/if
     * 
     * @param model
     * @return
     */
    @GetMapping("/if")
    public String ifTest(Model model) {

        Boolean boo = Boolean.TRUE;
        //Boolean boo = Boolean.FALSE;
        model.addAttribute("boo",boo);
        model.addAttribute("switchValue", SwitchValue.IF);

        return "thymeleaftest/index";
    
    }


    /**
     * formatの練習
     * http://localhost:8080/thymeleaftest/format
     * 
     * @param model
     * @return
     */
    @GetMapping("/format")
    public String formatTest(Model model) {

        // 現在時刻の生成
        model.addAttribute("now", LocalDateTime.now());

        Memo memo = memoService.getMemoById(1L);
        model.addAttribute("createdAt", memo.getCreatedAt());
        
        model.addAttribute("switchValue", SwitchValue.FORMAT);

        return "thymeleaftest/index";
    
    }



    /**
     * map
     * 
     * http://localhost:8080/thymeleaftest/map
     * 
     * @param model
     * @return
     */
    @GetMapping("/map")
    public String mapTest(Model model) {

        Map<String, Object> data = new HashMap<>();
        data.put("name", "テスト");
        data.put("message", "メッセージ");

        // Memoモデルのオブジェクトの場合
        Memo memo = memoService.getMemoById(1L);
        data.put("memo", memo);

        model.addAttribute("data", data);
        model.addAttribute("switchValue", SwitchValue.MAP);

        return "thymeleaftest/index";
    }

    /**
     * variable
     * 
     * http://localhost:8080/thymeleaftest/variable
     * URL上の各種パラメータの取り扱い
     * 
     * @param model
     * @return
     */
    @GetMapping("/variable")
    public String variableTest(Model model) {

        // ぱらめーた送信用フォームを画面に表示させる
        model.addAttribute("switchValue", SwitchValue.VARIABLE);

        return "thymeleaftest/index";
    }

    /**
     * variable
     * URL上の各種パラメータの取得テスト
     * 
     * @param model
     * @return
     */
    @GetMapping("/variable/{ID}")
    public String variableGetTest(@PathVariable("ID") Long id,
            @RequestParam("content") String content,
            @RequestParam("name") String name,
            Model model) {

        model.addAttribute("id", id);
        model.addAttribute("content", content);
        model.addAttribute("name", name);

        model.addAttribute("switchValue", SwitchValue.VARIABLE_RETURN);

        return "thymeleaftest/index";
    }

}
