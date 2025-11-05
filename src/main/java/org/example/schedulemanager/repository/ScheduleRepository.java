package org.example.schedulemanager.repository;

import org.example.schedulemanager.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 작성자명이 있을 때 - 전체 일정 조회
    List<Schedule> findAllByNameOrderByModifiedAtDesc(String name);

    // 작성자명이 없을 때 - 수정일 기준 (내림차순 정렬) - 전체 일정 조회
    List<Schedule> findAll();
}
