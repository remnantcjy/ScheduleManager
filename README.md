# CH3 일정 관리 앱 과제
- **Spring 9기 Spring 입문 - CH3 일정 관리 앱 과제**  
스프링 프레임워크를 활용하여 일정 관리 및 댓글 기능이 포함된 REST API 서버를 구현했습니다.
<br>

## 📌 프로젝트 소개
사용자가 일정을 등록, 조회, 수정, 삭제할 수 있으며<br>각 일정에 댓글을 추가할 수 있는 CRUD 기반의 일정 관리 API입니다.<br>
Spring Boot, JPA를 활용해 엔티티 간 연관관계 매핑 및 RESTful API 설계를 경험했습니다.

<br>

## ⚙️ 주요 기능
🗓 일정 (Schedule)
- 일정 생성 (POST /api/schedules)
- 전체 일정 조회 (GET /api/schedules)
- 단건 일정 조회 (GET /api/schedules/{id})
- 일정 수정 (PUT /api/schedules/{id})
- 일정 삭제 (DELETE /api/schedules/{id})

💬 댓글 (Comment)
- 댓글 생성 (POST /api/schedules/{id}/comments)
<br>

## 👩🏻‍💻 기술 스택
- **Language**: Java 17
- **Framework**: Spring Boot 3.5
- **ORM**: Spring Data JPA
- **Database**: MySQL
- **IDE**: IntelliJ IDEA
- **Build Tool**: Gradle
<br>

## 🗓 개발 기간
- 2025.11.03 ~ 2025.11.06
<br>

## 🗂 ERD
<img width="1844" height="868" alt="image" src="https://github.com/user-attachments/assets/3312c4f1-5bdf-44bc-b45b-329d2639ac78" />
<br><br>


## 📘 API 명세서
Postman API 문서를 통해 각 API의 요청/응답 예시를 확인할 수 있습니다.  
👉 [API 명세서 바로가기](https://documenter.getpostman.com/view/47338059/2sB3WqufU1)
<br><br>


## 🤓 트러블슈팅 기록
[일정관리앱 프로젝트 트러블슈팅 보러가기](https://remnantcjy.tistory.com/entry/%F0%9F%A4%93-%EC%9D%BC%EC%A0%95-%EA%B4%80%EB%A6%AC-%EC%95%B1-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-%F0%9F%92%A5)
