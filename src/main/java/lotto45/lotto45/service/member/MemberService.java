package lotto45.lotto45.service.member;

import lombok.RequiredArgsConstructor;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.exception.SameLoginIdException;
import lotto45.lotto45.repository.member.MemberRepository;
import lotto45.lotto45.service.Encryption;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Encryption encryption;

    public void save(Member member) {
        Optional<Member> optionalMember = this.memberRepository.findByLoginId(member.getLoginId());

        if (optionalMember.isPresent()) {
            throw new SameLoginIdException("You cannot register as a member with the same login ID.");
        } else {
            String encryptedPassword = this.encryption.encryptPassword(member.getPassword());
            member.setPassword(encryptedPassword);
            memberRepository.save(member);
        }
    }

    private String bytesToHex(byte[] bytes) {

        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
