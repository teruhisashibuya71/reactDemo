package com.example.angularDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URISyntaxException;

/**
 * 例外処理クラス
 * 基本的にこのクラスですべて処理する
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * チェック例外である `URI Exception`を共通で処理するメソッドです
     * UrlTestControllerの `①メソッド` で発生する例外を処理します
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(URISyntaxException.class)
    public ResponseEntity<String> handleURISyntaxException(URISyntaxException ex) {
        //URI関連の例外
        System.out.println("URI Syntax Exception: " + ex.getMessage());

        // エラーレスポンスを返す
        return new ResponseEntity<>("Invalid URI: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    /**
     * 非チェック例外を継承した自作例外クラス `MyselfException`を共通で処理するメソッドです
     * UrlTestControllerの `②メソッド` で発生する例外を処理します
     *
     * @param myEx
     * @return
     */
    @ExceptionHandler(MyselfException.class)
    public ResponseEntity<String> handleMyselfException(MyselfException myEx) {

        System.out.println("Myself Exception: " + myEx.getMessage());

        // エラーレスポンスを返す
        return new ResponseEntity<>("Conglaturation! We Get MyselfException: " + myEx.getMessage(), HttpStatus.BAD_REQUEST);
    }


    /**
     * TODO③のServiceメソッドで出たIlleagalArgumentExceptionを処理するメソッドです。
     *
     * @param IllegalEx
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    //public ResponseEntity<String> handleNoneCheckIllegalEx(IllegalArgumentException IllegalEx) {
    public String handleNoneCheckIllegalEx(IllegalArgumentException IllegalEx, Model model) {
        System.out.println("Illegal Argument Exception を検知しました: " + IllegalEx.getMessage());
        // viewではなくレスポンスエンティティなので、以下のテキストが画面に表示されます
        //return new ResponseEntity<>("Conglaturation! We Get IllegalException: " + IllegalEx.getMessage(), HttpStatus.BAD_REQUEST);

        model.addAttribute("errorMessage", "Illegal Argument Exception を検知しました");
        return "error";
    }


}
