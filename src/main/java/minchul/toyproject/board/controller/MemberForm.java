package minchul.toyproject.board.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberForm {

    private Long id;
    @NotEmpty(message = "필수 항목입니다.")
    private String username;
    private String password;

    public MemberForm(Long id, @NotEmpty(message = "필수 항목입니다.") String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
