package com.toy1.back.model.repository;

import com.toy1.back.model.dto.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ArticleRepositoryCustom {
    Page<PageResponseDto> searchAll(Pageable pageable);
}
