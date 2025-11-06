package org.example.schedulemanager.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;       // 일정 제목
    private String contents;    // 일정 내용
    private String name;        // 작성자
    private String password;    // 비밀번호

    // mappedBy - commentList 안의 schedule 필드가 직접 FK 관리
    @OneToMany(mappedBy = "schedule")
    private List<Comment> commentList;  // 댓글리스트 - 연간관계 매핑 필요

    public Schedule(String title, String contents, String name, String password) {
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.password = password;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
