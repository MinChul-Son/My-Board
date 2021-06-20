package minchul.toyproject.board.service;

import lombok.RequiredArgsConstructor;
import minchul.toyproject.board.domain.entity.Post;
import minchul.toyproject.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long savePost(Post post) {
        return boardRepository.save(post).getId();
    }
}
