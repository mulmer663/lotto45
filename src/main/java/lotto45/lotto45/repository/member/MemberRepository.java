package lotto45.lotto45.repository.member;

import lotto45.lotto45.domain.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    public Optional<Member> findByLoginId(String loginId) {
        return Optional.ofNullable(em.createQuery("SELECT m FROM Member m WHERE m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getSingleResult());
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

}
