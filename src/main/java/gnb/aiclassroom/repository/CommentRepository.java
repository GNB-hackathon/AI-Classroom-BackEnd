package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
