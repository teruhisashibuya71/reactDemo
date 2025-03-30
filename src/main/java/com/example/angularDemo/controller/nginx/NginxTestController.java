package com.example.angularDemo.controller.nginx;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Nginxの設定をテストするためのコントローラ
 */
@RequestMapping("/sitetype/{target_site}")
public class NginxTestController {


    /**
     * テストURL
     * http://localhost:8080/sitetype/my-site/home
     *
     * @return ホーム画面
     */
    @GetMapping("/home")
    public String showHome(@PathVariable("target_site") String target_site,
                           Model model) {

        // nginxの設定で設定したパスパラメータを出力
        System.out.println("target_siteの値: "+target_site);
        model.addAttribute("targetSite", target_site);

        return "nginx/home";
    }
}
