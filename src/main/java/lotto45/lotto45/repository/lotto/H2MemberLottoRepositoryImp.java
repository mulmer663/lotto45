package lotto45.lotto45.repository.lotto;

import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class H2MemberLottoRepositoryImp implements IMemberLottoRepository {

    @PersistenceContext
    private EntityManager em;
    private static final int MAX_COUNT = 10;

    @Override
    public void save(List<Lotto> lottoList, long memberId) {

        List<Lotto> savedLottoList = this.findByMemberId(memberId);
        int count = 0;

        for (Lotto lotto : lottoList) {
            if (savedLottoList.size() < MAX_COUNT) {
                em.persist(lotto);
            } else {
                em.remove(savedLottoList.get(count));
                em.persist(lotto);
                count++;
            }
        }
    }

    @Override
    public List<Lotto> findByMemberId(long memberId) {
        return em.createQuery("SELECT l FROM Lotto l JOIN l.member m" +
                              " WHERE m.id = :memberId" +
                              " ORDER BY l.dateTime", Lotto.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public void delete(List<Lotto> lottoList) {
        for (Lotto lotto : lottoList) {
            em.remove(lotto);
        }
    }
}
