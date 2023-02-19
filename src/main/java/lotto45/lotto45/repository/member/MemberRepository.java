package lotto45.lotto45.repository.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lotto45.lotto45.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.createQuery("SELECT m FROM Member m WHERE m.id = :id", Member.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public Optional<Member> findByLoginId(String loginId) {

        Optional<Member> findMember;

        try {
            findMember = Optional.ofNullable(em.createQuery("SELECT m FROM Member m WHERE m.loginId = :loginId", Member.class)
                    .setParameter("loginId", loginId)
                    .getSingleResult());
        } catch (NoResultException e) {
            findMember = Optional.empty();
        }

        return findMember;
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

    @Transactional
    public void remove(long memberId) {
        Member member = this.findById(memberId);
        em.remove(member);
    }
}
