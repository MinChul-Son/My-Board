package minchul.toyproject.board.repository;

import minchul.toyproject.board.domain.dto.PostDto;
import minchul.toyproject.board.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Post, Long> {

}
