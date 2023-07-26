package com.toy1.back.model.repository;


import com.toy1.back.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRe extends JpaRepository<Test, Long> {

}