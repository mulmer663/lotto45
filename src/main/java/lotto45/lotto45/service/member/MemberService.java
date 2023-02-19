package lotto45.lotto45.service.member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.exception.SameLoginIdException;
import lotto45.lotto45.repository.member.MemberRepository;
import lotto45.lotto45.service.Encryption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Encryption encryption;

    public void check(Member member) {
        Optional<Member> optionalMember = this.memberRepository.findByLoginId(member.getLoginId());

        if (optionalMember.isPresent()) {
            throw new SameLoginIdException("You cannot register as a member with the same login ID.");
        }
    }

    @Transactional
    public void save(Member member) {
        String encryptedPassword = this.encryption.encryptPassword(member.getPassword());
        member.setPassword(encryptedPassword);
        memberRepository.save(member);
    }

    public Member findById(Long memberId) {
        return this.memberRepository.findById(memberId);
    }

    @Transactional
    public void update(Long memberId, MemberDTO updateParam) {
        Member member = this.memberRepository.findById(memberId);
        String updatePassword = this.encryption.encryptPassword(updateParam.getPassword());

        member.setName(updateParam.getName());
        member.setPassword(updatePassword);
    }

    public List<Member> findAll() {
        return this.getOnlyMemberList();
    }

    public boolean verifyPreviousPassword(long memberId, String password) {
        Member member = this.memberRepository.findById(memberId);
        String previousPassword = member.getPassword();
        String currentPassword = this.encryption.encryptPassword(password);

        return currentPassword.equals(previousPassword);
    }

    @Transactional
    public void remove(long memberId) {
        this.memberRepository.remove(memberId);
    }

    private List<Member> getOnlyMemberList() {
        List<Member> members = this.memberRepository.findAll();
        members.removeIf(m -> m.getId().equals(1L) || m.getId().equals(2L));

        return members;
    }
}
