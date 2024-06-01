package gnb.aiclassroom.controller;

import gnb.aiclassroom.dto.LectureDTO;
import gnb.aiclassroom.dto.SearchResult;
import gnb.aiclassroom.entity.Lecture;
import gnb.aiclassroom.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;

    // 강의 등록
    @CrossOrigin
    @PostMapping(value = "/create",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> createLecture(@RequestPart LectureDTO lectureDTO,@RequestPart("vidio") MultipartFile file) {
        lectureService.createLecture(lectureDTO,file);
        return new ResponseEntity<>("Lecture created successfully", HttpStatus.CREATED);
    }

    // 강의 수정
    @CrossOrigin
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLecture(@PathVariable Long id, @RequestPart LectureDTO lectureDTO, @RequestPart("vidio") MultipartFile file) {
        lectureService.updateLecture(id, lectureDTO,file);
        return new ResponseEntity<>("Lecture updated successfully", HttpStatus.OK);
    }

    // 강의 삭제
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLecture(@PathVariable Long id) {
        lectureService.deleteLecture(id);
        return new ResponseEntity<>("Lecture deleted successfully", HttpStatus.OK);
    }

    // 전체 강의 조회
    @CrossOrigin
    @GetMapping("/search/all")
    public ResponseEntity<List<SearchResult>> AllLectures() {
        List<Lecture> allLectures = lectureService.findAll();
        if(!allLectures.isEmpty()){
            // Lecture entity 리스트를 searchResult 리스트로 변환
            List<SearchResult> searchResults = lectureService.convertToDTO(allLectures);
            return new ResponseEntity(searchResults, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    // 키워드로 강의 조회
    @CrossOrigin
    @GetMapping("/search/keyword")
    public ResponseEntity<List<Lecture>> searchByKeyword(@RequestParam String keyword) {
        List<Lecture> lectures = lectureService.searchByKeyword(keyword);
        if(!lectures.isEmpty()){
            // Lecture entity 리스트를 searchResult 리스트로 변환
            List<SearchResult> searchResults = lectureService.convertToDTO(lectures);
            return new ResponseEntity(searchResults, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    // 제목으로 강의 조회
    @CrossOrigin
    @GetMapping("/search/title")
    public ResponseEntity<List<Lecture>> searchByTitle(@RequestParam String title) {
        List<Lecture> lectures = lectureService.searchByTitle(title);

        if(!lectures.isEmpty()){
            // Lecture entity 리스트를 searchResult 리스트로 변환
            List<SearchResult> searchResults = lectureService.convertToDTO(lectures);
            return new ResponseEntity(searchResults, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    // 튜터 명으로 강의 조회
    @CrossOrigin
    @GetMapping("/search/tutorNickname")
    public ResponseEntity<List<Lecture>> searchByTutorNickname(@RequestParam String nickname) {
        List<Lecture> lectures = lectureService.searchByTutorNickname(nickname);

        if(!lectures.isEmpty()){
            // Lecture entity 리스트를 searchResult 리스트로 변환
            List<SearchResult> searchResults = lectureService.convertToDTO(lectures);
            return new ResponseEntity(searchResults, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    // 카테고리로 조회
    @CrossOrigin
    @GetMapping("/search/category")
    public ResponseEntity<List<Lecture>> searchByCategory(@RequestParam String category) {
        List<Lecture> lectures = lectureService.searchByCategory(category);

        if(!lectures.isEmpty()){
            // Lecture entity 리스트를 searchResult 리스트로 변환
            List<SearchResult> searchResults = lectureService.convertToDTO(lectures);
            return new ResponseEntity(searchResults, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }


}
