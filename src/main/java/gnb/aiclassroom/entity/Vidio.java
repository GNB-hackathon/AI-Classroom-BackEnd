package gnb.aiclassroom.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vidio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vidioId;

    private String originalName; // 사진 원본 이름

    private String url;

    private Long size; // 사이즈

    @OneToOne
            (       fetch = FetchType.LAZY,
                    targetEntity = Lecture.class
            )
    @JoinColumn(name = "lecture_id")
    @JsonManagedReference
    private Lecture lecture;



}
