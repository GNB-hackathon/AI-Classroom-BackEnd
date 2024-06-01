package gnb.aiclassroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 활성화
public class AiclassroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiclassroomApplication.class, args);
	}

}
