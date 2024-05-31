package gnb.aiclassroom.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberDTO {

    private String id;

    private String name;

    private String password;

    private String passwordCheck;

    private String email;

    private String introduce;

    private String nickname;

    private String profile;

    private String type;

}
