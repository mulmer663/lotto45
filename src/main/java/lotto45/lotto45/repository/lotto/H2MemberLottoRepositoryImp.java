package lotto45.lotto45.repository.lotto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class H2MemberLottoRepositoryImp implements IMemberLottoRepository {

    @PersistenceContext
    private EntityManager em;
    private static final int MAX_COUNT = 50;

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
