package org.example.schedulemanager.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    private String title;
    private String contents;
    private String password;
}
