package com.example.angularDemo.controller;

import com.example.angularDemo.model.UrlTestModel;
import com.example.angularDemo.service.UrlTestModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

/**
 * URLテストモデルのコントローラー
 * 例外処理の動作テストを行うために作成
 * チェック例外・非チェック例外に応じて、処理の流れを確認しています
 *
 * @author 2025/04/05
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/url/test")
public class UrlTestModelController implements ModelTestController {

    /** final句を忘れずに */
    private final UrlTestModelService UrlService;


    // --


    /**
     * TODO ①
     * Serviceクラスでスローされる例外を `GlobalExceptionHandler` で処理します
     * 可読性を優先するため`try catch`を使用しない形式にするため、throws句を使用しています
     *
     * http://localhost:8080/url/test/throws
     *
     * @throws URISyntaxException
     *
     */
    @GetMapping("/throws")
    public void wayOfThrows() throws URISyntaxException {

        //Serviceクラスで発生する例外を
        UrlService.findByIdWithURIException(1L);
    }

    /**
     * TODO ②
     * Serviceクラスで例外がスローされますが、自作の例外クラスを使用します。
     * これにより、Serviceクラスの例外を直接`GlobalExceptionHandler`で処理することができます。
     * よって、このメソッドにはtrows句は必要ありません
     *
     * http://localhost:8080/url/test/selfexception
     *
     */
    @GetMapping("/selfexception")
    public void wayOfNoThrows() {

        //Serviceクラスで発生する例外を
        UrlService.findByIdWithSelfException(2L);
    }


    /**
     * TODO ③
     * try catchタイプ
     * http://localhost:8080/url/test/trycatch
     *
     */
    @GetMapping("/trycatch")
    public String wayOfTryCatchException(Model model) {

        //Serviceクラスで発生する例外を
        UrlService.findByIdWithTryCatch(3L);

        model.addAttribute("methodName", "wayOfTryCatchExceptionメソッドより遷移");
        return "home";
    }


    /**
     * TODO ④ NoSuchが発生するメソッドを呼ぶ処理の例外ハンドリングを検討する
     * テスト用URL
     * http://localhost:8080/url/test/nosuchalgorithm
     *
     * @param model
     * @return
     */
    @GetMapping("/nosuchalgorithm")
    // interfaceを実装していると throews句かけないかな?
    // コントローラでthrowsした NoSuchAlgorithmException は、ハンドラーで処理される
    public String wayOfNoSuchAlgorithmException(Model model) throws NoSuchAlgorithmException {
        //public String wayOfNoSuchAlgorithmException(Model model) {

        // try catch使わない場合
        //Serviceクラスでチェック例外であるNoSuchAlgorithmExceptionが発生する
        UrlService.findByIdWithNoSuchAlgorithmException(4L);
        // TODO コントローラーのメソッドにthrows句を付与する場合、OPEN-APIで生成するinterfaceファイルにthrows句が付与されるようにしなくてはいけない


//        try {
//            UrlService.findByIdWithNoSuchAlgorithmException(4L);
//        } catch (NoSuchAlgorithmException alEx) {
//            controllerでcatchした場合は、大抵log出力してerrorページを返却することになる
//
//        }

        model.addAttribute("methodName", "wayOfNoSuchAlgorithmExceptionメソッドより遷移");
        return "home";
    }
}
