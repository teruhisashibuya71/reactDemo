package com.example.angularDemo.enums;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

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

    public String getKomojiValue() {
        return komojiValue;
    }
    public void setKomojiValue(String komojiValue) {
        this.komojiValue = komojiValue;
    }

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }




    /**
     * enumの値をすべて取得するメソッド
     *
     * @return すべてのenumの値
     */
    public static List<CategoryEnum> getAllValues() {
        return Arrays.asList(CategoryEnum.values());
    }
}
