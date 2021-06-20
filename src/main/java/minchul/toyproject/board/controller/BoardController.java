package minchul.toyproject.board.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import minchul.toyproject.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Getter
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "This is home";
    }

    @GetMapping("/post/new")
    public String newPostForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }
}
