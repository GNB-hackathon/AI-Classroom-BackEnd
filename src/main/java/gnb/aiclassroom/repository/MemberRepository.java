package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
