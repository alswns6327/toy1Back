package com.toy1.back.controller;

import com.toy1.back.model.dto.*;
import com.toy1.back.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("/page")
    public ResponseEntity<Page<PageResponseDto>> pageArticle(@RequestParam(name = "page") int page) {
        return ResponseEntity.ok(articleService.pageArticle(page));
    }

    @GetMapping("/one")
    public ResponseEntity<ArticleResponseDto> getOneArticle(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(articleService.oneArticle(id));
    }

    @PostMapping("/")
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody CreateArticleRequestDto request) {
        return ResponseEntity.ok(articleService.postArticle(request.getTitle(), request.getBody()));
    }

    @GetMapping("/change")
    public ResponseEntity<ArticleResponseDto> getChangeArticle(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(articleService.oneArticle((id)));
    }

    @PutMapping("/")
    public ResponseEntity<ArticleResponseDto> putChangeArticle(@RequestBody ChangeArticleRequestDto request) {
        return ResponseEntity.ok(articleService.changeArticle(
                request.getId(), request.getTitle(), request.getBody()
        ));
    }

    @DeleteMapping("/one")
    public ResponseEntity<MessageDto> deleteArticle(@RequestParam(name = "id") Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok(new MessageDto("Success"));
    }

}