package minchul.toyproject.board.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minchul.toyproject.board.domain.dto.PostDto;
import minchul.toyproject.board.domain.dto.SearchDto;
import minchul.toyproject.board.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import static minchul.toyproject.board.domain.entity.QPost.*;

@Slf4j
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostDto> findPageDynamicQuery(Category category, int myPost, SearchDto searchDto, String username, Pageable pageable) {
        QueryResults<PostDto> results = queryFactory
                .select(Projections.constructor(PostDto.class, post))
                .from(post)
                .where(categoryEq(category), usernameEq(myPost, username), searchDtoEq(searchDto))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.createdDate.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanExpression categoryEq(Category categoryCond) {
        return categoryCond != null ? post.category.eq(categoryCond) : null;
    }
    private BooleanExpression usernameEq(int myPost, String usernameCond) {
        return myPost != 0 ? post.username.eq(usernameCond) : null;
    }
    private BooleanExpression searchDtoEq(SearchDto searchDtoCond) {
        if (searchDtoCond.getSearchValue() != "" && searchDtoCond.getSearchKey() != null) {
            if (searchDtoCond.getSearchKey().equals("title")) {
                log.info("제목 검색");
                return post.title.contains(searchDtoCond.getSearchValue());

            } else if (searchDtoCond.getSearchKey().equals("content")) {
                log.info("내용 검색");
                return post.content.contains(searchDtoCond.getSearchValue());

            } else {
                log.info("이름 검색");
                return post.username.eq(searchDtoCond.getSearchValue());
            }
        }
        return null;
    }

}
