package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.TutorComment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<TutorComment, Long> {
}
