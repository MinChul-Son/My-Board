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
    private Long age;
    private String password;
    private int enabled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public Member(String username, Long age, String password, int enabled, Role role) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }
}
