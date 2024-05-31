package gnb.aiclassroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tutorId;

    private String name;

    private String id;

    private  String password;

    private String email;

    private String introduce;

    private String nickname;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL) // 튜터의 강의 목록
    private Set<Lecture> lectures = new HashSet<>(); // lecture 의 member 와 mapping 되어있다

    @OneToMany(mappedBy = "tutor" , cascade = CascadeType.ALL) // 한 member 는 N개의 댓글을 달 수 있음
    private Set<TutorComment> comments = new HashSet<>();
}
