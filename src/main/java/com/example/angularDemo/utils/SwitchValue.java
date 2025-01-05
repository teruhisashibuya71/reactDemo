package com.example.angularDemo.utils;


/**
 * thymeleafの出力分岐用のenum
 */
public enum SwitchValue {

    IF("if"),
    FORMAT("format"),
    MAP("map"),
    VARIABLE("variable"),
    VARIABLE_RETURN("variable_return");

    //フィールド
    private final String switchValue;

    // コンストラクタ
    SwitchValue(String switchValue) {
        this.switchValue = switchValue;
    }

    /**
     * フロントのthymeleaf上で呼び出す必要なし
     * 記述しておくだけで、springのEL式が評価される際、暗黙的に呼び出される
     * th:switch="${switchValue.toString()}"しなくて良い
     * 
     */
    @Override
    public String toString() {
        return switchValue;
    }
}
