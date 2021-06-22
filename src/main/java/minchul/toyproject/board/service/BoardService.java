package minchul.toyproject.board.service;

import lombok.RequiredArgsConstructor;
import minchul.toyproject.board.controller.PostForm;
import minchul.toyproject.board.domain.dto.PostDto;
import minchul.toyproject.board.domain.entity.Post;
import minchul.toyproject.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    @Transactional
    public PostDto getPost(Long id) throws Exception {
        Optional<Post> byId = boardRepository.findById(id);
        if (byId.isPresent()) { // null이 아니면
            Post post = byId.get();
            post.increaseViewCount();
            PostDto postDto = new PostDto(post);
            return postDto;
        } else {
            throw new Exception("해당 게시물을 찾지 못했습니다.");
        }
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public Long updatePost(Long id, PostForm postForm) throws Exception{
        Optional<Post> byId = boardRepository.findById(id);
        if (byId.isPresent()) {
            Post post = byId.get();
            post.changePost(post, postForm);
            return post.getId();
        } else {
            throw new Exception("해당 게시물을 찾지 못했습니다.");
        }

    }

}
