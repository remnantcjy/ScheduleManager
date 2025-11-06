package org.example.schedulemanager.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    // 일정 아이디
    private Long id;
    private String contents;
    private String name;
    private String password;
}
