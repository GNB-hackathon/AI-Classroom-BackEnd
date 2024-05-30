package gnb.aiclassroom.controller;

import gnb.aiclassroom.dto.MemberDTO;
import gnb.aiclassroom.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    // 로그인
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody MemberDTO loginDto){
        boolean isAuthenticated = memberService.authenticateMember(loginDto.getId(),loginDto.getPassword());
        System.out.println(isAuthenticated);
        if(isAuthenticated){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 회원가입
    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity signup(@RequestBody MemberDTO signupDto){
        return memberService.signupRequest(signupDto);
    }

}
