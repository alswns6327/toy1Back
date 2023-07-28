package com.toy1.back.service;

import com.toy1.back.config.SecurityUtil;
import com.toy1.back.model.dto.ArticleResponseDto;
import com.toy1.back.model.dto.PageResponseDto;
import com.toy1.back.model.entity.Article;
import com.toy1.back.model.entity.Member;
import com.toy1.back.model.repository.ArticleRepository;
import com.toy1.back.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleResponseDto oneArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
            return ArticleResponseDto.of(article, false);
        } else {
            Member member = memberRepository.findById(Long.parseLong(authentication.getName())).orElseThrow();
            boolean result = article.getMember().equals(member);
            return ArticleResponseDto.of(article, result);
        }
    }

    public Page<PageResponseDto> pageArticle(int pageNum) {
        return articleRepository.searchAll(PageRequest.of(pageNum - 1, 20));
    }

    @Transactional
    public ArticleResponseDto postArticle(String title, String body) {
        Member member = isMemberCurrent();
        Article article = Article.createArticle(title, body, member);
        return ArticleResponseDto.of(articleRepository.save(article), true);
    }

    @Transactional
    public ArticleResponseDto changeArticle(Long id, String title, String body) {
        Article article = authorizationArticleWriter(id);
        return ArticleResponseDto.of(articleRepository.save(Article.changeArticle(article, title, body)), true);
    }

    @Transactional
    public void deleteArticle(Long id) {
        Article article = authorizationArticleWriter(id);
        articleRepository.delete(article);
    }

    public Member isMemberCurrent() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    public Article authorizationArticleWriter(Long id) {
        Member member = isMemberCurrent();
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        if (!article.getMember().equals(member)) {
            throw new RuntimeException("로그인한 유저와 작성 유저가 같지 않습니다.");
        }
        return article;
    }
}
