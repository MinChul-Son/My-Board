package minchul.toyproject.board.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minchul.toyproject.board.domain.entity.Category;
import org.springframework.context.annotation.Primary;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostForm {

    private Long id;
    @NotEmpty(message = "필수 항목입니다.")
    private String title;
    private String username;
    private String content;
    private Category
            category;

    public PostForm(Long id, @NotEmpty(message = "필수 항목입니다.") String title, String username, String content, Category category) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.content = content;
        this.category = category;
    }
}
