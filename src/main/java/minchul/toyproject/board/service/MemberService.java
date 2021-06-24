package minchul.toyproject.board.service;

import lombok.RequiredArgsConstructor;
import minchul.toyproject.board.domain.entity.Member;
import minchul.toyproject.board.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(Member member) {
        String encodedPw = passwordEncoder.encode(member.getPassword());
        member.encodingPw(member, encodedPw); // 패스워드 인코딩
        member.giveAuth(member);

        return memberRepository.save(member).getId();
    }
}
