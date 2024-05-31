package gnb.aiclassroom.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LectureDTO {

    private String title;

    private String category;

    private String tutor;

    private String content;

}
