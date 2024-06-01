package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.TutorComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<TutorComment, Long> {
}
