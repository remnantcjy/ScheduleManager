package org.example.schedulemanager.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanager.dto.CreateScheduleRequest;
import org.example.schedulemanager.dto.CreateScheduleResponse;
import org.example.schedulemanager.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    // ScheduleService 주입
    private final ScheduleService scheduleService;

    // Lv 1. 일정 생성
    @PostMapping("/schedules")
    public CreateScheduleResponse createSchedule(
//            @RequestParam String title,
//            @RequestBody String contents,
//            @RequestParam String name,
//            @RequestParam String password
            @RequestBody CreateScheduleRequest request
    ) {
        CreateScheduleResponse result = scheduleService.save(request);

        return result;
    }
}
