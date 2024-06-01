package gnb.aiclassroom.service;

import gnb.aiclassroom.dto.CommentDTO;
import gnb.aiclassroom.dto.LectureDTO;
import gnb.aiclassroom.entity.Lecture;
import gnb.aiclassroom.entity.StudentComment;
import gnb.aiclassroom.entity.Tutor;
import gnb.aiclassroom.entity.Vidio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentService {


    public void createStudentComment(Long lectureId, CommentDTO commentDTO) {
        StudentComment comment= new StudentComment();
        comment.setContent(commentDTO.getContent());
        comment.setLectureId(lectureId);
        comment.setUserId(commentDTO.getUserId()); // 가정: CommentDTO에 userId가 포함되어 있음
        comment.setUserType("Student"); // 또는 다른 로직에 따라
        commentRepository.save(comment);
    }

    public void createTutorComment(Long lectureId, CommentDTO commentDTO) {
    }

    public void commentAll(Long lectureId, CommentDTO commentDTO) {
    }

    public void updateComment(Long lectureId, CommentDTO commentDTO) {
    }

    public void deleteComment(Long lectureId, CommentDTO commentDTO) {
    }
}
