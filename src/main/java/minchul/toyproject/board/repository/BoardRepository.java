package minchul.toyproject.board.repository;

import minchul.toyproject.board.domain.entity.Category;
import minchul.toyproject.board.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    Optional<Post> findById(Long id);

    @EntityGraph(attributePaths = {"member"})
    @Query("select p from Post p where p.member.username = :username")
    Page<Post> findPostByMember(@Param("username") String username, Pageable pageable);

    @Query("select p from Post p where p.category = :category")
    Page<Post> findByCategory(@Param("category") Category category, Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    @Query("select p from Post p where p.member.username = :username and p.category = :category")
    Page<Post> findByCategoryAndUsername(@Param("category") Category category, @Param("username") String username, Pageable pageable);
}
