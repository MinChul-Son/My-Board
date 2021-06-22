package minchul.toyproject.board.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {

    @Id @GeneratedValue
    @Column(name = "role_id")
    private Long id;
    private String role_value;
    @OneToMany(mappedBy = "role")
    private List<Member> members = new ArrayList<>();
}
