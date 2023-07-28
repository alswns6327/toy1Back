package com.toy1.back.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeArticleRequestDto {

    private Long id;
    private String title;
    private String body;
}
