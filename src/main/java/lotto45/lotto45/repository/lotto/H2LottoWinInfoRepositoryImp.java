package lotto45.lotto45.repository.lotto;

import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class H2LottoWinInfoRepositoryImp implements ILottoWinInfoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void saveAll(List<LottoWinningInfo> winningInfos) {

        for (LottoWinningInfo winningInfo : winningInfos) {
            em.persist(winningInfo);
        }
    }

    @Override
    public List<LottoWinningInfo> findByRounds(int rounds) {
        return em.createQuery("SELECT l FROM LottoWinningInfo l WHERE l.drwNo = :drwNo", LottoWinningInfo.class)
                .setParameter("drwNo", rounds)
                .getResultList();
    }

    @Override
    public List<LottoWinningInfo> findAll() {
        return em.createQuery("SELECT l FROM LottoWinningInfo l", LottoWinningInfo.class)
                .getResultList();
    }
}
