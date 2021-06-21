package minchul.toyproject.board.service;

import lombok.RequiredArgsConstructor;
import minchul.toyproject.board.domain.dto.PostDto;
import minchul.toyproject.board.domain.entity.Post;
import minchul.toyproject.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long savePost(Post post) {
        return boardRepository.save(post).getId();
    }

    @Transactional
    public List<PostDto> postList() {
        List<Post> postList = boardRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();

        for (Post post : postList) {
            postDtoList.add(new PostDto(post));
        }
        return postDtoList;
    }
}
