package org.example.schedulemanager.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanager.dto.*;
import org.example.schedulemanager.entity.Schedule;
import org.example.schedulemanager.repository.ScheduleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    // ScheduleRepository 주입
    private final ScheduleRepository scheduleRepository;

    // Create - 일정 생성
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

    // Read - 다건 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll(String name) {

        // 스케줄 리스트 선언
        List<Schedule> schedules;

        // name이 있을 때
        if (name != null) {

            // 작성자명 기준으로 전체 일정 조회 및 반환
            schedules = scheduleRepository.findAllByNameOrderByModifiedAtDesc(name);
        } else {

            // name이 없을 때
            // 수정일 기준 (내림차순 정렬)로 전체 일정 조회 및 반환
            schedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedAt"));
        }

        // GetAllScheduleResponse형으로 반환할 dtos 리스트 생성
        List<GetScheduleResponse> dtos = new ArrayList<>();

        // Schedule -> GetScheduleResponse 타입으로 형변환
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );

            // 변환된 dto를 리스트에 추가
            dtos.add(dto);
        }

        // 반환
        return dtos;
    }

    // Read - 단건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long id) {

        // id에 해당하는 일정 반환 / 예외 처리
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // Update - 일정 수정
    @Transactional
    public UpdateScheduleResponse update(Long id, UpdateScheduleRequest request) {

        // id에 해당하는 일정 반환 / 예외 처리
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        // 비밀번호 불일치 시
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 일치 시 - 일정 업데이트 (제목, 내용)
        schedule.update(
                request.getTitle(),
                request.getContents()
        );

        // 업데이트 응답 반환 (제목, 내용, 수정일)
        return new UpdateScheduleResponse(
                schedule.getTitle(),
                schedule.getContents()
        );
    }

    // Delete - 일정 삭제
    @Transactional
    public void delete(Long id, DeleteScheduleRequest request) {

        // id가 있는지 확인
        boolean existence = scheduleRepository.existsById(id);

        // id가 있으면 해당하는 일정 반환
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        // 유저가 없는 경우
        if (!existence) {
            throw new IllegalStateException("없는 유저입니다.");
        } else {
            // 유저가 있을 때, 비밀번호 확인
            if (schedule.getPassword().equals(request.getPassword())) {

                // 비밀번호 일치 시
                scheduleRepository.deleteById(id);
            } else {
                throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
            }
        }
    }
}
