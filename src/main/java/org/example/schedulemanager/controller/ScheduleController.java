package org.example.schedulemanager.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanager.dto.CreateScheduleRequest;
import org.example.schedulemanager.dto.CreateScheduleResponse;
import org.example.schedulemanager.dto.GetScheduleResponse;
import org.example.schedulemanager.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Lv 2. 일정 조회
    // 전체 일정 조회 - 작성자명 기준으로 조회도 가능
    @GetMapping("/schedules")
    public List<GetScheduleResponse> getSchedules() {

        List<GetScheduleResponse> result = scheduleService.findAll();

        return result;
    }
}
