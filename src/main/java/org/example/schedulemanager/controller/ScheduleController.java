package org.example.schedulemanager.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanager.dto.*;
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
    public CreateScheduleResponse createSchedule(@RequestBody CreateScheduleRequest request) {

        CreateScheduleResponse result = scheduleService.save(request);

        return result;
    }

    // Lv 2. 일정 조회
    // 전체 일정 조회 - 작성자명 기준으로 조회도 가능
    @GetMapping("/schedules")
    public List<GetScheduleResponse> getSchedules(@RequestParam(required = false) String name) {

        List<GetScheduleResponse> result = scheduleService.findAll(name);

        return result;
    }

    // 선택 일정 조회 - id로 식별
    @GetMapping("/schedules/{id}")
    public GetScheduleResponse getOneSchedule(@PathVariable Long id) {

        GetScheduleResponse result = scheduleService.getOne(id);

        return result;
    }

    // Lv 3. 일정 수정
    @PutMapping("/schedules/{id}")
    public UpdateScheduleResponse updateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequest request
    ) {

        UpdateScheduleResponse result = scheduleService.update(id, request);

        return result;
    }

    // Lv 4. 일정 삭제
    @DeleteMapping("/schedules/{id}")
    public void delete(
            @PathVariable Long id,
            @RequestBody DeleteScheduleRequest request
    ) {
        scheduleService.delete(id, request);
    }

}
