package minchul.toyproject.board.controller;

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
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/signIn")
    public String singIn() {
        return "members/signIn";
    }

    @GetMapping("/member/signUp")
    public String signUp(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/signUp";
    }

    @PostMapping("/member/signUp")
    public String singUp(@Valid MemberForm form, BindingResult result) {
        Member newMember = new Member(form.getUsername(), form.getPassword());
        newMember.giveAuth(newMember);
        memberService.save(newMember);
        return "redirect:/";
    }
}
