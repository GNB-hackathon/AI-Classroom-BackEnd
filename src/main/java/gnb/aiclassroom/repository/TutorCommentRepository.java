package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.TutorComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorCommentRepository extends JpaRepository<TutorComment, Long> {
}
