package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.StudentComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCommentRepostiory extends JpaRepository<StudentComment,Long> {
}
