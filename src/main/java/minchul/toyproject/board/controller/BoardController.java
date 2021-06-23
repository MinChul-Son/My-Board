package minchul.toyproject.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minchul.toyproject.board.domain.dto.PostDto;
import minchul.toyproject.board.domain.entity.Post;
import minchul.toyproject.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Getter
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/board")
    public String postList(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<PostDto> dtoPage = boardService.postList(page);
        model.addAttribute("postList", dtoPage);
        return "posts/list";
    }

    @GetMapping("/board/post/new")
    public String newPostForm(Model model, Authentication auth) {
        model.addAttribute("auth", auth);
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }


    @PostMapping("/board/post/new")
    public String savePost(@Valid PostForm form, BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            log.info("오류가 있습니다.");
        }
        Post newPost = new Post(auth.getName(), form.getTitle(), form.getContent());
        boardService.savePost(newPost);
        return "redirect:/board";
    }

    @GetMapping("/board/post/{postId}")
    public String postDetail(@PathVariable("postId") Long id, Model model) throws Exception {
        try {
            PostDto postDto = boardService.getPost(id);
            model.addAttribute("post", postDto);
            return "posts/detail";
        } catch (Exception e) {
            log.error("해당 게시물을 찾기 못햇습니다.");
            return "redirect:/board";
        }
    }

    @DeleteMapping("/board/post/{postId}")
    public String postDelete(@PathVariable("postId") Long id) {
        boardService.deletePost(id);
        return "redirect:/board";
    }

    @GetMapping("/board/post/edit/{postId}")
    public String postEdit(@PathVariable("postId") Long id, Model model) throws Exception {
        try {
            PostDto postDto = boardService.getPost(id);
            PostForm postForm = new PostForm(postDto.getId(), postDto.getTitle(), postDto.getUsername(), postDto.getContent());
            log.info(postDto.getContent());
            log.info(postDto.getUsername());
            log.info(postDto.getTitle());
            model.addAttribute("post", postForm);
            return "posts/edit";
        } catch (Exception e) {
            log.error("해당 게시물을 찾기 못햇습니다.");
            return "redirect:/board";
        }
    }

    @PutMapping("/board/post/edit/{postId}")
    public String postUpdate(@PathVariable("postId") Long id, @Valid PostForm form, BindingResult result) throws Exception {
        boardService.updatePost(id, form);
        return "redirect:/board";
    }
}
