package gnb.aiclassroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Lecture extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    private String title;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor; // 업로드한 튜터

    @ManyToMany(mappedBy = "ownedLectures", fetch = FetchType.LAZY)
    private Set<Student> owners = new HashSet<>(); // 소장하고 있는 학생들

    private String content; // 내용
}
