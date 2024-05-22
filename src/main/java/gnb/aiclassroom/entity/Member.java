package gnb.aiclassroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String id;

    private  String password;

    private String email;

    private String introduce;

    private String nickname;

    private String profile;

    @OneToOne // 연관관계 주인
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) // 강의 목록
    private List<Lecture> lectures; // lecture 의 member 와 mapping 되어있다

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL) // 한 member 는 N개의 댓글을 달 수 있음
    private List<Comment> comments;
}
