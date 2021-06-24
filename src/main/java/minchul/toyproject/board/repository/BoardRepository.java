package minchul.toyproject.board.repository;

import minchul.toyproject.board.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    Optional<Post> findById(Long id);
}
