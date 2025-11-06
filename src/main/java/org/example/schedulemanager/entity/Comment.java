package org.example.schedulemanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;    // 댓글
    private String name;        // 작성자
    private String password;    // 비밀번호

    // 외래키 지정 - 외래키 가진 Many쪽이 주인
    @ManyToOne(fetch = FetchType.LAZY)  // 가능한 LAZY 사용!
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;  // 일정
}




