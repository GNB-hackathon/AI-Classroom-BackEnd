package gnb.aiclassroom.service;

import gnb.aiclassroom.dto.LectureDTO;
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


    public List<LectureDTO> searchLectures(String keyword) {
        return null;
    }
}
