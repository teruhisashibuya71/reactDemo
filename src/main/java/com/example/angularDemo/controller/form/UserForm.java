package com.example.angularDemo.controller.form;

import lombok.Data;

/**
 * ユーザー フォームクラス
 * 
 */
@Data
public class UserForm {

    private Long id;

    private String name;

    private Integer age;


    public void setId(long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }


    public Integer getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
