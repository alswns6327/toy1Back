package com.toy1.back.model.repository;

import com.toy1.back.model.entity.Article;
import com.toy1.back.model.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    List<Recommend> findAllByArticle(Article article);
}
