package gnb.aiclassroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;

    private String id;

    private String password;

    private String email;

    private String introduce;

    private String nickname;

    private String profile;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "lecture_owners",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    private Set<Lecture> ownedLectures = new HashSet<>(); // 소장하고 있는 강의들

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudentComment> comments = new HashSet<>(); // 학생이 단 댓글
}
