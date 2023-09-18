package study.springjpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"})
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private int age;
    private String username;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    public void changeTeam(Team team){
        this.team  = team;
        team.getMembers().add(this);
    }


    public Member(String username) {
        this.username = username;
    }
}
