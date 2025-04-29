package com.example.angularDemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne
    @JoinColumn(name = "memo_id")
    private Memo memo;

}
