package gnb.aiclassroom.service;

import gnb.aiclassroom.dto.MemberDTO;
import gnb.aiclassroom.entity.Student;
import gnb.aiclassroom.entity.Tutor;
import gnb.aiclassroom.repository.StudentRepository;
import gnb.aiclassroom.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;

    //private final PasswordEncoder passwordEncoder;

    // 학생 로그인
    public  boolean authenticateStudent(String id, String password){

        // 사용자 아이디로 사용자 정보를 조회
       Student student = studentRepository.findById(id); // id 로 DB에서 사용자 정보 조회
        // 사용자 정보가 존재하고, 입력된 비밀번호와 db에 저장된 정보가 일치하는지 확인
        if (student != null && password.equals(student.getPassword())){
            return true; // 인증 성공
        }
        return false; // 인증 실패
    }

    public  boolean authenticateTutor(String id, String password){

        // 사용자 아이디로 사용자 정보를 조회
        Tutor tutor = tutorRepository.findById(id); // id 로 DB에서 사용자 정보 조회
        // 사용자 정보가 존재하고, 입력된 비밀번호와 db에 저장된 정보가 일치하는지 확인
        if (tutor != null && password.equals(tutor.getPassword())){
            return true; // 인증 성공
        }
        return false; // 인증 실패
    }

    // 회원가입 요청
    public ResponseEntity<String> signupRequest(MemberDTO signupDto) {
        try {
            if (signupDto.getType().equals("student")){
                Student newStudent = new Student();
                newStudent.setName(signupDto.getName());
                newStudent.setId(signupDto.getId());
                newStudent.setPassword(signupDto.getPassword());
                newStudent.setNickname(signupDto.getNickname());
                newStudent.setEmail(signupDto.getEmail());
                studentRepository.save(newStudent);
            }else if(signupDto.getType().equals("tutor")){
                Tutor newTutor = new Tutor();
                newTutor.setName(signupDto.getName());
                newTutor.setId(signupDto.getId());
                newTutor.setPassword(signupDto.getPassword());
                newTutor.setNickname(signupDto.getNickname());
                newTutor.setEmail(signupDto.getEmail());
                tutorRepository.save(newTutor);
            }

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
