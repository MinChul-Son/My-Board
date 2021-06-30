package minchul.toyproject.board.repository;

import minchul.toyproject.board.domain.dto.PostDto;
import minchul.toyproject.board.domain.dto.SearchDto;
import minchul.toyproject.board.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<PostDto> findPageDynamicQuery(Category category, int myPost, SearchDto searchDto, String username, Pageable pageable);
}
