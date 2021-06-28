package minchul.toyproject.board.domain.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import minchul.toyproject.board.repository.BoardRepository;
import minchul.toyproject.board.repository.MemberRepository;
import minchul.toyproject.board.service.BoardService;
import minchul.toyproject.board.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static minchul.toyproject.board.domain.entity.QPost.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;
    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
        Member member1 = new Member("member1", "1234");
        Member member2 = new Member("member2", "1234");

        memberService.save(member1);
        memberService.save(member2);

        Post post1 = new Post("member1", "test1", "this is test1",Category.자유);
        Post post2 = new Post("member2", "test2", "this is test2", Category.자바);
        boardService.savePost(post1);
        boardService.savePost(post2);
    }

    @Test
    void 연관관계테스트() {
        Post post = queryFactory
                .selectFrom(QPost.post)
                .where(QPost.post.username.eq("member1"))
                .fetchOne();

        System.out.println("member_username = " + post.getMember().getUsername());
        System.out.println("member_role = " + post.getMember().getRole());
        System.out.println("post_username = " + post.getUsername());
        System.out.println("post = " + post.getMember());

        List<Post> result = queryFactory
                .selectFrom(QPost.post)
                .fetch();
        for (Post post1 : result) {
            System.out.println("post1 = " + post1.getMember());
        }
    }

    @Test
    void 게시물에서회원엔티티조회() {
        Optional<Post> byId = boardRepository.findById(19L);
        Post post = byId.get();
        assertThat(post.getMember().getUsername()).isEqualTo("son");
    }

    @Test
    void 내게시물조회() {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<Post> result = boardRepository.findPostByMember("son", pageRequest);
        for (Post post1 : result) {
            System.out.println("post1 = " + post1);
        }
        assertThat(result.getContent().size()).isEqualTo(3);

    }

    @Test
    void 카테고리로조회() {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<Post> result = boardRepository.findByCategory(Category.자유, pageRequest);
        List<Post> content = result.getContent();
        for (Post post1 : content) {
            System.out.println("post1 = " + post1);
        }
    }


}