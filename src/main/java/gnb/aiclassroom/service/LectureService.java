package gnb.aiclassroom.service;

import gnb.aiclassroom.dto.LectureDTO;
import gnb.aiclassroom.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    
    private final LectureRepository lectureRepository;


    public void createLecture(LectureDTO lectureDTO) {
    }

    public void updateLecture(Long id, LectureDTO lectureDTO) {
    }

    public void deleteLecture(Long id) {
    }

    public List<LectureDTO> searchLectures(String keyword) {
        return null;
    }
}
