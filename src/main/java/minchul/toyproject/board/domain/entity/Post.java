package minchul.toyproject.board.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import minchul.toyproject.board.controller.PostForm;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String username;
    private String title;
    private String content;
    private int viewCount;

    public Post(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public Post changePost(Post post, PostForm postForm) {
        post.title = postForm.getTitle();
        post.content = postForm.getContent();
        return post;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
}
