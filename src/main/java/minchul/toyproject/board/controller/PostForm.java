package minchul.toyproject.board.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PostForm {

    @NotEmpty(message = "필수 항목입니다.")
    private String title;
    private String username;
    private String content;
}
