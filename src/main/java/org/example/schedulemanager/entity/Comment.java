package org.example.schedulemanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)  // 가능한 LAZY 사용!
    @JoinColumn(name = "id")
    private Schedule schedule;  // 일정

    public Comment(String contents, String name, String password) {
        this.contents = contents;
        this.name = name;
        this.password = password;
    }

}




