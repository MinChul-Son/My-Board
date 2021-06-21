package minchul.toyproject.board.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostForm {

    private Long id;
    @NotEmpty(message = "필수 항목입니다.")
    private String title;
    private String username;
    private String content;

    public PostForm(Long id, @NotEmpty(message = "필수 항목입니다.") String title, String username, String content) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.content = content;
    }
}
