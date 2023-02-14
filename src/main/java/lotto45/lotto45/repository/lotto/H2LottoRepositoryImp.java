package lotto45.lotto45.repository.lotto;

import lotto45.lotto45.domain.lotto.Lotto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Repository
public class H2LottoRepositoryImp implements LottoNumberRepository {

    @PersistenceContext
    private EntityManager em;
    private static final Queue<Lotto> lottoQueue = new LinkedList<>();
    private static final int MAX_COUNT = 50;

    @Override
    @Transactional
    public void save(Lotto lotto) {

        int lottoCount = this.findAll().size();

        if (lottoQueue.size() < 8) {
            lottoQueue.add(lotto);
        } else if (lottoQueue.size() == 8) {
            lottoQueue.poll();
            lottoQueue.add(lotto);
        }

        if (lottoCount++ < MAX_COUNT) {
            em.persist(lotto);
        } else {
            Lotto deletedLotto = em.createQuery("SELECT l FROM Lotto l ORDER BY l.dateTime", Lotto.class)
                    .setMaxResults(1)
                    .getSingleResult();
            em.remove(deletedLotto);
            em.persist(lotto);
        }
    }

    @Override
    public List<Lotto> findByRounds(int rounds) {
        return em.createQuery("SELECT l FROM Lotto l WHERE l.rounds = :rounds", Lotto.class)
                .setParameter("rounds", rounds)
                .getResultList();
    }

    @Override
    public Queue<Lotto> lastLottoNumber8() {
        return lottoQueue;
    }

    @Override
    public List<Lotto> findAll() {
        return em.createQuery("SELECT l FROM Lotto l", Lotto.class)
                .getResultList();
    }
}
