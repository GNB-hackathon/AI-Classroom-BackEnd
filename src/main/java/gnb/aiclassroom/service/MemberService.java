package gnb.aiclassroom.service;

import gnb.aiclassroom.dto.MemberDTO;
import gnb.aiclassroom.entity.Member;
import gnb.aiclassroom.entity.Type;
import gnb.aiclassroom.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    //private final PasswordEncoder passwordEncoder;

//    public boolean authenticateMember(String id, String password){
//
//        // 사용자 아이디로 사용자 정보를 조회
//
//
//    }

    // 회원가입 요청
    public ResponseEntity<String> signupRequest(MemberDTO signupDto) {
        try {
            Member newMember = new Member();
            newMember.setName(signupDto.getName());
            newMember.setId(signupDto.getId());
            newMember.setPassword(signupDto.getPassword());
            newMember.setNickname(signupDto.getNickname());
            newMember.setEmail(signupDto.getEmail());

            Type type = Type.valueOf(signupDto.getType().toUpperCase());
            newMember.setType(type);

            memberRepository.save(newMember);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("회원가입 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    // 비밀번호 재확인
//    public ResponseEntity passwordCheck(){
//
//    }
}
