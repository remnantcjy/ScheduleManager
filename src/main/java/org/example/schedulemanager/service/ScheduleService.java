package org.example.schedulemanager.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanager.dto.CreateScheduleRequest;
import org.example.schedulemanager.dto.CreateScheduleResponse;
import org.example.schedulemanager.dto.GetScheduleResponse;
import org.example.schedulemanager.entity.Schedule;
import org.example.schedulemanager.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    // ScheduleRepository 주입
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        // request에서 값 꺼내와 일정 객체로 변환
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                request.getName(),
                request.getPassword()
        );

        // 레포지토리에 일정 저장 -> 저장된 일정 반환
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // 레포지토리에 데이터 처리된 일정을 사용자에게 응답
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getPassword(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll() {
        // 레포지토리에서 모든 일정을 일정리스트에 담아줌
        List<Schedule> schedules = scheduleRepository.findAll();

        // GetAllScheduleResponse형으로 반환할 dtos 리스트 생성
        List<GetScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }
}
