package minchul.toyproject.board.domain.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import minchul.toyproject.board.repository.BoardRepository;
import minchul.toyproject.board.repository.MemberRepository;
import minchul.toyproject.board.service.BoardService;
import minchul.toyproject.board.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static minchul.toyproject.board.domain.entity.QPost.*;
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

        Post post1 = new Post("member1", "test1", "this is test1");
        Post post2 = new Post("member2", "test2", "this is test2");
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


}