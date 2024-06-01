package gnb.aiclassroom.dto;

import gnb.aiclassroom.entity.Tutor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@RequiredArgsConstructor
public class LectureDTO {

    private String title;

    private String category;

    private String tutorId; // 실제 튜터의 아이디

    private String content;


}
