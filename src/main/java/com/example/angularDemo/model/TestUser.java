package com.example.angularDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * ユーザーモデル
 * テストクラスの処理を確認するためのテスト用モデルです
 * 
 */
@Entity 
@Data
public class TestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private boolean isDeleted;

    // Memoとのつながり

//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public Integer getAge() {
//        return this.age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
}
