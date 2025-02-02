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
     * ①.if文章の練習
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
        //model.addAttribute("switchValue", SwitchValue.IF);
        model.addAttribute("testIf","testIf");

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
        
        //model.addAttribute("switchValue", SwitchValue.FORMAT);
        model.addAttribute("testDateFormat", "testDateFormat");

        return "thymeleaftest/index";
    
    }


    /**
     * ③.map値の出力テスト
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
        model.addAttribute("mapTesat", "mapTest");
        //model.addAttribute("switchValue", SwitchValue.MAP);

        return "thymeleaftest/index";
    }

    /**
     * ④.URL上の各種パラメータの取り扱いテスト
     * フォーム表示(要在庫確認)のGETメソッド
     * http://localhost:8080/thymeleaftest/variable
     * 
     * @param model
     * @return
     */
    @GetMapping("/variable")
    public String variableTest(Model model) {
        // ぱらめーた送信用フォームを画面に表示させる
        //model.addAttribute("switchValue", SwitchValue.VARIABLE);
        model.addAttribute("formGetTest", "formGetTest");
        return "thymeleaftest/index";
    }

    /**
     * ④-2.URL上の各種パラメータの取り扱いテスト
     * フォーム入力値を扱うPOSTメソッド
     * http://localhost:8080/thymeleaftest/variable
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

        //model.addAttribute("switchValue", SwitchValue.VARIABLE_RETURN);
        model.addAttribute("formPostTest", "formPostTest");

        return "thymeleaftest/index";
    }

    /**
     * ⑤.th:withの使い方確認
     * th:withの使い方をまとめた処理です。
     * http://localhost:8080/thymeleaftest/with
     * 
     * @param model
     * @return
     */
    @GetMapping("/with")
    public String variableGetTest(Model model) {

        model.addAttribute("testWith", "testWith");
        return "thymeleaftest/index";
    }

    /**
     * ⑥.th:classappendの使い方確認
     * http://localhost:8080/thymeleaftest/classappend
     * 
     * @param model
     * @return
     */
    @GetMapping("/classappend")
    public String checkClassAppend(Model model) {

        model.addAttribute("testClassAppend", "testClassAppend");
        
        //model.addAttribute("appendTestValue", null);
        model.addAttribute("appendTestValue", "appendTestValue");
        
        return "thymeleaftest/index";
    }

    /**
     * ⑦.変数の結合処理のテスト
     * http://localhost:8080/thymeleaftest/variableunion
     * 
     * @param model
     * @return
     */
    @GetMapping("/variableunion")
    public String checkVariableUnion(Model model) {

        model.addAttribute("myouji", "澁谷");
        model.addAttribute("namae", "晶久");
        
        model.addAttribute("variableUnion", "variableUnion");
        
        return "thymeleaftest/index";
    }


    /**
     * ⑦.th:switch〜th:caseの練習
     * http://localhost:8080/thymeleaftest/switchcase
     * 
     * @param model
     * @return
     */
    @GetMapping("/switchcase")
    public String testSwitchCase(Model model) {

        model.addAttribute("testCase", "testCase");
        
        model.addAttribute("testCaseValue", 10);
        
        return "thymeleaftest/index";
    }
    
}