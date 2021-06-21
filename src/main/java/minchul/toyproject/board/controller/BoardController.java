package minchul.toyproject.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minchul.toyproject.board.domain.dto.PostDto;
import minchul.toyproject.board.domain.entity.Post;
import minchul.toyproject.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Getter
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String postList(Model model) {
        List<PostDto> result = boardService.postList();
        model.addAttribute("postList", result);
        return "posts/list";
    }

    @GetMapping("/post/new")
    public String newPostForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }


    @PostMapping("/post/new")
    public String savePost(@Valid PostForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.info("오류가 있습니다.");
        }
        Post newPost = new Post(form.getUsername(), form.getTitle(), form.getContent());
        boardService.savePost(newPost);
        log.info("Content:{}, Title: {}", newPost.getContent(),newPost.getTitle());
        log.info("Content:{}, Title: {}", form.getContent(),form.getTitle());

        return "redirect:/";
    }
}
