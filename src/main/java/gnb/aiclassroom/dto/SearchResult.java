package gnb.aiclassroom.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchResult {

    private String createdDate; // 업로드 일시

    private String modifiedDate; // 수정 일시

    private String title; // 강의 영상 제목

    private String category; // 영상 카테고리

    private String tutorId; // 실제 튜터의 아이디

    private String content; // 내용

    private String url; // 영상 url

}
