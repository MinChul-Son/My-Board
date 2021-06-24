package minchul.toyproject.board.service;

import lombok.RequiredArgsConstructor;
import minchul.toyproject.board.controller.PostForm;
import minchul.toyproject.board.domain.dto.PostDto;
import minchul.toyproject.board.domain.entity.Member;
import minchul.toyproject.board.domain.entity.Post;
import minchul.toyproject.board.repository.BoardRepository;
import minchul.toyproject.board.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long savePost(Post post) {
        Member findMember = memberRepository.findByUsername(post.getUsername());
        post.writerFromSession(post, findMember);
        return boardRepository.save(post).getId();
    }

    public Page<PostDto> postList(int page) {
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<Post> pageMap = boardRepository.findAll(pageRequest);
        Page<PostDto> toDtoMap = pageMap.map(post -> new PostDto(post));
        return toDtoMap;
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
