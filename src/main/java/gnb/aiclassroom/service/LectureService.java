package gnb.aiclassroom.service;

import gnb.aiclassroom.dto.LectureDTO;
import gnb.aiclassroom.dto.SearchResult;
import gnb.aiclassroom.entity.Lecture;
import gnb.aiclassroom.entity.Tutor;
import gnb.aiclassroom.entity.Vidio;
import gnb.aiclassroom.repository.LectureRepository;
import gnb.aiclassroom.repository.TutorRepository;
import gnb.aiclassroom.repository.VidioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    
    private final LectureRepository lectureRepository;
    private final FirebaseService firebaseService;
    private final TutorRepository tutorRepository;
    private final VidioRepository vidioRepository;


    public void createLecture(LectureDTO lectureDTO,MultipartFile file) {

        // 매물 정보 저장
        Lecture lecture = new Lecture();

        lecture.setTitle(lectureDTO.getTitle());
        lecture.setCategory(lectureDTO.getCategory());
        lecture.setContent(lectureDTO.getContent());

        Tutor tutor = tutorRepository.findById(lectureDTO.getTutorId());
        lecture.setTutor(tutor); // 실제 튜터 객체

        lectureRepository.save(lecture);

        // 강의 동영상 firebase 상에 업로드
        if (file == null || file.isEmpty()) { // 파일이 null이거나 비어 있는 경우 예외 처리
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일이 없거나 비어 있습니다.");
        }

        try {
            // 영상 파일 이름
            String originalFilename = file.getOriginalFilename();

            // 파일 크기
            Long fileSize = file.getSize();

            // firebase storage에 업로드
            String url = firebaseService.uploadVidio(file, originalFilename);

            // DB에 저장
            Vidio vidio = new Vidio();
            vidio.setOriginalName(originalFilename);
            vidio.setSize(fileSize);
            vidio.setUrl(url);
            vidio.setLecture(lecture);
            vidioRepository.save(vidio);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 중 오류가 발생했습니다.", e);
        }
    }

    public void updateLecture(Long id, LectureDTO lectureDTO,MultipartFile file) {

        // 강의 정보 수정
        Lecture originalLecture = lectureRepository.findById(id).get();

        originalLecture.setTitle(lectureDTO.getTitle());
        originalLecture.setCategory(lectureDTO.getCategory());
        originalLecture.setContent(lectureDTO.getContent());

        Tutor tutor = tutorRepository.findById(lectureDTO.getTutorId());
        originalLecture.setTutor(tutor); // 실제 튜터 객체

        lectureRepository.save(originalLecture);

        // 강의 동영상 firebase 상에 업로드
        if (file == null || file.isEmpty()) { // 파일이 null이거나 비어 있는 경우 예외 처리
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일이 없거나 비어 있습니다.");
        }

        try {
            // 영상 파일 이름
            String originalFilename = file.getOriginalFilename();

            // 파일 크기
            Long fileSize = file.getSize();

            // firebase storage에 업로드
            String url = firebaseService.uploadVidio(file, originalFilename);

            // DB에 저장

            Vidio originalVidio = vidioRepository.findById(originalLecture.getVidio().getVidioId()).get();
            originalVidio.setOriginalName(originalFilename);
            originalVidio.setSize(fileSize);
            originalVidio.setUrl(url);
            originalVidio.setLecture(originalLecture);
            vidioRepository.save(originalVidio);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 중 오류가 발생했습니다.", e);
        }
    }

    public void deleteLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id).get();
        lectureRepository.delete(lecture);
    }

    // 전체 강의 검색
    public List<Lecture> findAll(){
        return (List<Lecture>) lectureRepository.findAll();
    }

    // 키워드로 강의 검색 (제목/강의/튜터명)
    public List<Lecture> searchByKeyword(String keyword) {
        return lectureRepository.searchByKeyword(keyword);
    }

    // 강의 제목으로 검색
    public List<Lecture> searchByTitle(String title) {
        return lectureRepository.searchByTitle(title);
    }

    // 튜터 명으로 검색
    public List<Lecture> searchByTutorNickname(String nickname) {
        return lectureRepository.searchByTutorNickname(nickname);
    }

    // 카테고리로 검색
    public List<Lecture> searchByCategory(String category) {
        return lectureRepository.searchByCategory(category);
    }


    // Entity 를 DTO 로 변환
    public List<SearchResult> convertToDTO(List<Lecture> lectures) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<SearchResult> results = new ArrayList<>();
        for (Lecture lecture : lectures) {
            SearchResult searchResult = new SearchResult();
            searchResult.setTitle(lecture.getTitle());
            searchResult.setCategory(lecture.getCategory());
            searchResult.setUrl(lecture.getVidio().getUrl());
            searchResult.setContent(lecture.getContent());
            searchResult.setCreatedDate(formatter.format(lecture.getCreatedDate()));
            searchResult.setModifiedDate(formatter.format(lecture.getModifiedDate()));
            searchResult.setTutorId(lecture.getTutor().getId());

            results.add(searchResult);
        }
        return results;
    }

}
