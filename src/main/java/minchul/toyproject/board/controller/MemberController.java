package minchul.toyproject.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minchul.toyproject.board.domain.entity.Member;
import minchul.toyproject.board.domain.entity.Role;
import minchul.toyproject.board.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Getter
@RequiredArgsConstructor
@Controller
@Slf4j
@Api(tags = {"회원"})
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/signIn")
    @ApiOperation(value = "로그인")
    public String singIn() {
        return "members/signIn";
    }

    @GetMapping("/member/signUp")
    @ApiOperation(value = "회원가입")
    public String signUp(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/signUp";
    }

    @PostMapping("/member/signUp")
    @ApiOperation(value = "새로운 회원 저장")
    public String singUp(@Valid MemberForm form, BindingResult result) {
        Member newMember = new Member(form.getUsername(), form.getPassword());
        newMember.giveAuth(newMember);
        memberService.save(newMember);
        return "redirect:/";
    }
}
