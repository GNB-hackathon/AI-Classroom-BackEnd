package gnb.aiclassroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Lecture extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne 이 연관관계의 주인
    @JoinColumn(name = "member_id")
    private Member member;

}
