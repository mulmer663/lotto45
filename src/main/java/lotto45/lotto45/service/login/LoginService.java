package lotto45.lotto45.service.login;

import lombok.RequiredArgsConstructor;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.repository.member.MemberRepository;
import lotto45.lotto45.service.Encryption;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final Encryption encryption;

    /**
     * 먼저 로그인ID로 회원 정보를 찾고 그 다음, 비번이 맞는지 확인 후 맞으면 해당 member 객체 넘기고
     * 아니면 null 반환
     */
    public Member login(String loginId, String password) {
        String encryptedPassword = encryption.encryptPassword(password);

        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(encryptedPassword))
                .orElse(null);
    }

    public Member getMember(String loginId) {
        return memberRepository.findByLoginId(loginId)
                .orElse(null);
    }
}

