package minchul.toyproject.board.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minchul.toyproject.board.domain.entity.Post;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostDto(Post post) {
        this.id = post.getId();
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.viewCount = post.getViewCount();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getLastModifiedDate();
    }


}
