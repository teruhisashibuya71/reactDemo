package com.example.angularDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.net.URL;

@Entity
@Data
public class UrlTestModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ロゴ画像URL
     */
    //private URL logoImgUrl;
    private String logoImgUrl;

    /**
     * フッターロゴ画像URL
     */
    //private URL footerLogUrl;
    private String footerLogUrl;

    /**
     * スマートフォンロゴ画像URL
     */
    //private URL spLogUrl;
    private String spLogUrl;
}
