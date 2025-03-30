package com.example.angularDemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import com.example.angularDemo.controller.form.UserForm;
import com.example.angularDemo.model.TestUser;
import com.example.angularDemo.service.TestUserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final TestUserService userService;
    


    /**
     * ユーザー一覧画面の表示
     * 
     * http://localhost:8080/user/all
     * 
     * @param model
     * @return
     */
    @GetMapping("/all")
    public String showAllUser(Model model){

        // すべてのユーザーを取得
        List<TestUser> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);

        // viewを表示する場合は最初のスラッシュ必要ない
        return "user/list";
    }


    /**
     * 新規登録画面の表示
     * http://localhost:8080/user/new
     * 
     * @param user
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String showCreate(Model model){

        //フォーム生成して画面に渡すだけ
        model.addAttribute("payload", new UserForm());
            
        // 登録画面を表示
        return "user/new";
    }

    /**
     * 新規登録処理
     * 
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/new")
    public String createUser(@ModelAttribute UserForm form, Model model){

        // 登録処理を実行
        userService.createUser(form);
            
        // リダイレクトの場合はURL指定なので先頭にスラッシュは必ず必要だよ
        return "redirect:/user/all";
    }
    
    /**
     * ユーザー削除
     * リクエストボディで値を受け取る形式
     * 
     * @param id
     * @return
     */
    // @PostMapping("/delete")
    // public String deleteUser(@RequestParam("id") Long id){

    //     // 登録処理を実行
    //     userService.deleteUser(id);
            
    //     // リダイレクトの場合はURL指定なので先頭にスラッシュは必ず必要だよ
    //     return "redirect:/user/all";
    // }


/**
     * ユーザー削除
     * パスパラメーター形式
     * 
     * @param id
     * @return
     */
    @PostMapping("/delete/{ID}")
    public String deleteUser(@PathVariable("ID") Long id){

        // 登録処理を実行
        userService.deleteUser(id);
            
        // リダイレクトの場合はURL指定なので先頭にスラッシュは必ず必要だよ
        return "redirect:/user/all";
    }
    
}
