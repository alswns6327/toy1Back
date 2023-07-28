package com.toy1.back.model.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long articleId;
    private String body;
}
