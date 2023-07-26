package com.toy1.back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {


    private final com.toy1.back.service.Test test;


    @GetMapping("/test")
    public ResponseEntity<List<com.toy1.back.model.entity.Test>> setMemberNickname() {
        System.out.println(111);
        return ResponseEntity.ok(test.find());
    }

}
