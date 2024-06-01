package gnb.aiclassroom.dto;

import gnb.aiclassroom.entity.StudentComment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private String content;

    private String nickname;

    private String userType;


}
