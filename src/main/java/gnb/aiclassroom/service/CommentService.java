package gnb.aiclassroom.service;

import gnb.aiclassroom.dto.CommentDTO;
import gnb.aiclassroom.entity.*;
import gnb.aiclassroom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TutorCommentRepository tutorcommentRepository;
    private final StudentCommentRepostiory studentCommentRepostiory;
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;


    public void createComment(Long lectureId, CommentDTO commentDTO) {

        if(commentDTO.getUserType().equals("student")){
            StudentComment comment= new StudentComment();
            comment.setContent(commentDTO.getContent());

            Optional<Lecture> lecture = lectureRepository.findById(lectureId);

            comment.setLecture(lecture.get());

            Student student = studentRepository.findById(commentDTO.getId());
            comment.setStudent(student);

            studentCommentRepostiory.save(comment);

        }else if (commentDTO.getUserType().equals("tutor")){
            TutorComment comment= new TutorComment();
            comment.setContent(commentDTO.getContent());

            Optional<Lecture> lecture = lectureRepository.findById(lectureId);

            comment.setLecture(lecture.get());

            Tutor tutor = tutorRepository.findById(commentDTO.getId());
            comment.setTutor(tutor);

            tutorcommentRepository.save(comment);
        }

    }

    public void commentAll(Long lectureId, CommentDTO commentDTO) {
    }

    public void updateComment(Long lectureId, CommentDTO commentDTO) {
    }

    public void deleteComment(Long lectureId, CommentDTO commentDTO) {
    }
}
