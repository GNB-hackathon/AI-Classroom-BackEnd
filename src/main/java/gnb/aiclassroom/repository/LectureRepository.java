package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.Lecture;
import org.springframework.data.repository.CrudRepository;

public interface LectureRepository extends CrudRepository<Lecture,Long> {
}
