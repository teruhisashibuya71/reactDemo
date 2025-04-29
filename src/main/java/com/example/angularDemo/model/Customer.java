package com.example.angularDemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    // Memoモデルとのつながりを設定
    // 接続するための外部キーをnameに設定する
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Memo> memos;

}
