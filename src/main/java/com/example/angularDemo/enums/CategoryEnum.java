package com.example.angularDemo.enums;

import lombok.Data;

/**
 * テスト用のenumです
 *
 */
//@Data
public enum CategoryEnum {

    /** `name` `komojiValue` `number` とする */
    HOGE("hoge",1),
    FUGA("fuga",2),
    FIZZ("fizz",3),
    BUZZ("buzz",4);

    /** TestEnum.name() メソッドで 大文字の値は取得可能 */

    /** 1つめの値 */
    private String komojiValue;

    /** 2つめの値 */
    private Integer number;


    /**
     * enumにコンストラクタは必須
     *
     * @param komojiValue
     * @param number
     */
    CategoryEnum(String komojiValue, Integer number) {
        this.komojiValue = komojiValue;
        this.number = number;
    }

    public void setKomojiValue(String komojiValue) {
        this.komojiValue = komojiValue;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public String getKomojiValue() {
        return komojiValue;
    }
}
