package com.toy1.back.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String test1;

    @Column(nullable = false)
    private String test2;

    @Builder
    public Test(Long id, String test1, String test2) {
        this.id = id;
        this.test1 = test1;
        this.test2 = test2;
    }
}
