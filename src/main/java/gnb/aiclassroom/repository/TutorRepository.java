package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.Student;
import gnb.aiclassroom.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    @Query("select t from Tutor t where t.id = :id")
    Tutor findById(@Param("id") String id);
}
