package minchul.toyproject.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minchul.toyproject.board.domain.dto.PostDto;
import minchul.toyproject.board.domain.dto.SearchDto;
import minchul.toyproject.board.domain.entity.Category;
import minchul.toyproject.board.domain.entity.Member;
import minchul.toyproject.board.domain.entity.Post;
import minchul.toyproject.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
@Getter
@Slf4j
@Api(tags = {"게시판"})
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/board")
    @ApiOperation(value = "게시물 목록")
    public String postList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) Category category,
            @RequestParam(defaultValue = "0") int myPost,
            @ModelAttribute("searchDto") SearchDto searchDto,
            Model model, Authentication auth){
        Page<PostDto> dtoPage = boardService.postList(page, category, myPost, searchDto, auth.getName());
        model.addAttribute("postList", dtoPage);
        model.addAttribute("categories", Arrays.asList(Category.values()));
        model.addAttribute("myPost", myPost);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("username", auth.getName());
        return "posts/list";
    }

//    @GetMapping("/board/mypost")
//    public String myPost(@RequestParam(defaultValue = "0") int page, Model model, Authentication auth) {
//        Page<PostDto> dtoPage = boardService.myList(page, auth.getName());
//        model.addAttribute("postList", dtoPage);
//        return "posts/list";
//    }

    @GetMapping("/board/post/new")
    @ApiOperation(value = "새로운 게시물 작성")
    public String newPostForm(Model model, Authentication auth) {
        model.addAttribute("auth", auth);
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("categories", Arrays.asList(Category.values()));
        return "posts/createPostForm";
    }


    @PostMapping("/board/post/new")
    @ApiOperation(value = "새로운 게시물 저장")
    public String savePost(@Valid PostForm form, BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            log.info("오류가 있습니다.");
        }
        Post newPost = new Post(auth.getName(), form.getTitle(), form.getContent(), form.getCategory());
        boardService.savePost(newPost);
        return "redirect:/board";
    }

    @GetMapping("/board/post/{postId}")
    @ApiOperation(value = "게시물 조회")
    public String postDetail(@PathVariable("postId") Long id, Model model, Authentication auth) throws Exception {
        try {
            PostDto postDto = boardService.getPost(id);
            model.addAttribute("post", postDto);
            Boolean isWriter = boardService.isWriter(postDto, auth.getName());
            model.addAttribute("isWriter", isWriter);
            return "posts/detail";
        } catch (Exception e) {
            log.error("해당 게시물을 찾기 못햇습니다.");
            return "redirect:/board";
        }
    }

    @DeleteMapping("/board/post/{postId}")
    @ApiOperation(value = "게시물 삭제")
    public String postDelete(@PathVariable("postId") Long id) {
        boardService.deletePost(id);
        return "redirect:/board";
    }

    @GetMapping("/board/post/edit/{postId}")
    @ApiOperation(value = "게시물 수정")
    public String postEdit(@PathVariable("postId") Long id, Model model) throws Exception {
        try {
            PostDto postDto = boardService.getPost(id);
            PostForm postForm = new PostForm(postDto.getId(), postDto.getTitle(), postDto.getUsername(), postDto.getContent(), postDto.getCategory());
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
    @ApiOperation(value = "수정한 게시물 저장")
    public String postUpdate(@PathVariable("postId") Long id, @Valid PostForm form, BindingResult result) throws Exception {
        boardService.updatePost(id, form);
        return "redirect:/board";
    }
}
