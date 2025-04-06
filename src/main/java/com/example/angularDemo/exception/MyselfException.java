package com.example.angularDemo.exception;


public class MyselfException extends RuntimeException {

    private final String errorCode;


    /**
     * 例外を `throw new` する時のためにコンストラクタは必須
     * 継承したRuntimeExceptionのコンストラクタを呼び出す。
     * エラーコードのフィールドには値をセットできるようにしておくのが一般的な使い方か
     */
    public MyselfException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


    /**
     * エラーコードを取得するゲッターは用意しておく
     * @return
     */
    public String getErrorCode() {
        return errorCode;
    }

}
