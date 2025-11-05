package org.example.schedulemanager.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleResponse {

    private final String title;
    private final String contents;

    public UpdateScheduleResponse(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
