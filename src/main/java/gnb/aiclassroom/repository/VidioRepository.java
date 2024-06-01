package gnb.aiclassroom.repository;

import gnb.aiclassroom.entity.Vidio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VidioRepository extends JpaRepository<Vidio,Long> {

}
