package com.toy1.back.service;

import com.toy1.back.config.SecurityUtil;
import com.toy1.back.model.dto.RecommendDto;
import com.toy1.back.model.entity.Article;
import com.toy1.back.model.entity.Member;
import com.toy1.back.model.entity.Recommend;
import com.toy1.back.model.repository.ArticleRepository;
import com.toy1.back.model.repository.MemberRepository;
import com.toy1.back.model.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final RecommendRepository recommendRepository;

    public RecommendDto allRecommend(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        List<Recommend> recommends = recommendRepository.findAllByArticle(article);
        int size = recommends.size();
        if (size == 0) {
            return RecommendDto.noOne();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
            return new RecommendDto(size, false);
        } else {
            Member member = memberRepository.findById(Long.parseLong(authentication.getName())).orElseThrow();
            boolean result = recommends.stream().anyMatch(recommend -> recommend.getMember().equals(member));
            return new RecommendDto(size, result);
        }
    }

    @Transactional
    public void createRecommend(Long id) {
        Member member = memberRepository.findById(
                        SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));

        Recommend recommend = new Recommend(member, article);
        recommendRepository.save(recommend);
    }

    @Transactional
    public void removeRecommend(Long id) {
        Member member = memberRepository.findById(
                        SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        Recommend recommend = recommendRepository.findAllByArticle(article)
                .stream()
                .filter(r -> r.getMember().equals(member))
                .findFirst()
                .orElseThrow(() ->  new RuntimeException("추천이 없습니다."));

        recommendRepository.delete(recommend);
    }
}
