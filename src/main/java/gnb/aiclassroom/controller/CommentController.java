package gnb.aiclassroom.controller;

import gnb.aiclassroom.dto.CommentDTO;
import gnb.aiclassroom.dto.LectureDTO;
import gnb.aiclassroom.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lecture")
public class CommentController {

    private final CommentService commentService;


    // 학생 댓글 작성
    @CrossOrigin
    @PostMapping("/{lectureId}/student/comment")
    public ResponseEntity<String> createStudentComment(@PathVariable Long lectureId, @RequestBody CommentDTO commentDTO) {
        commentService.createStudentComment(lectureId,commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully.");
    }

    // 튜터 댓글 작성
    @CrossOrigin
    @PostMapping("/{lectureId}/tutor/comment")
    public ResponseEntity<String> createTutorComment(@PathVariable Long lectureId, @RequestBody CommentDTO commentDTO) {
        commentService.createTutorComment(lectureId,commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully.");
    }

    // 특정 강의 댓글 조회
    @CrossOrigin
    @GetMapping("/{lectureId}/comment/all")
    public ResponseEntity<String> readComment(@PathVariable Long lectureId, @RequestBody CommentDTO commentDTO) {
        commentService.commentAll(lectureId,commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully.");
    }

    // 강의 댓글 수정
    @CrossOrigin
    @PutMapping("/{lectureId}/comment/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long lectureId, @RequestBody CommentDTO commentDTO) {
        commentService.updateComment(lectureId,commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully.");
    }

    // 댓글 삭제
    @CrossOrigin
    @DeleteMapping("/{lectureId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long lectureId, @RequestBody CommentDTO commentDTO) {
        commentService.deleteComment(lectureId,commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully.");
    }




}
