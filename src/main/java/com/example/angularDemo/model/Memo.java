package com.example.angularDemo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

/**
 * エンティティ追加する時の手順
 * ①.必要なライブラリをcursorに聞いて gradleに追加 → ./gradlew build
 * ②.エンティティの追記を先に行い、予測変換を介してアノテーションを追加する
 * ③.import文も一緒に追加されて正常に動作する
 */
@Entity 
@Data
public class Memo {

    /** JPA用の記述忘れないこと */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    // Memoモデルとのつながりを設定
    // 接続するための外部キーをnameに設定する
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "detail_id")
    private Detail detail;


    public Memo(){}

    public Memo(Long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}