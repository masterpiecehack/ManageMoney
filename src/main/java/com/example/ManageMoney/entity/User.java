package com.example.ManageMoney.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter // ゲッターを自動生成
@Setter // セッターを自動生成
@NoArgsConstructor // 無引数コンストラクタを自動生成
@AllArgsConstructor // 全フィールドを引数に持つコンストラクタを自動生成
@Entity // JPAのエンティティとしてマーク
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    // Lombokによりゲッター、セッター、コンストラクタは自動生成されるため、手動で定義する必要はありません
}