package minchul.toyproject.board.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String password;
    private int enabled;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void encodingPw(Member member, String encodedPw) {
        member.password = encodedPw;
    }

    public void giveAuth(Member member) {
        member.role = Role.ROLE_USER;
        member.enabled = 1;
    }
}
