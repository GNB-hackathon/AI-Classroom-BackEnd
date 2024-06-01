package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.Lecture;
import gnb.aiclassroom.entity.Vidio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends CrudRepository<Lecture,Long> {

    @Query("SELECT l FROM Lecture l " +
            "JOIN l.tutor t " +
            "WHERE (l.title LIKE %:keyword% " +
            "OR t.nickname LIKE %:keyword% " +
            "OR l.category LIKE %:keyword%)")
    List<Lecture> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT l FROM Lecture l WHERE l.title LIKE %:title%")
    List<Lecture> searchByTitle(@Param("title") String title);

    @Query("SELECT l FROM Lecture l JOIN l.tutor t WHERE t.nickname LIKE %:nickname%")
    List<Lecture> searchByTutorNickname(@Param("nickname") String tutorNickname);

    @Query("SELECT l FROM Lecture l WHERE l.category LIKE %:category%")
    List<Lecture> searchByCategory(@Param("category") String category);



}
