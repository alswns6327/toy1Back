package com.toy1.back.service;

import com.toy1.back.model.repository.TestRe;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Test {
    private final TestRe testRe;
    private final PasswordEncoder passwordEncoder;

    public List<com.toy1.back.model.entity.Test> find() {
        System.out.println(222);
        return testRe.findAll();
    }
}