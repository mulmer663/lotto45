package lotto45.lotto45.repository.lotto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class H2LottoWinInfoRepositoryImp implements ILottoWinInfoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveAll(List<LottoWinningInfo> winningInfos) {

        for (LottoWinningInfo winningInfo : winningInfos) {
            em.persist(winningInfo);
        }
    }

    @Override
    public Optional<LottoWinningInfo> findByLatestRounds() {
        Optional<LottoWinningInfo> lottoWinningInfo;

        try {
            lottoWinningInfo = Optional.ofNullable(
                    em.createQuery("SELECT l FROM LottoWinningInfo l ORDER BY l.drwNo DESC", LottoWinningInfo.class)
                            .setMaxResults(1)
                            .getSingleResult());
        } catch (NoResultException e) {
            lottoWinningInfo = Optional.empty();
        }

        return lottoWinningInfo;
    }

    @Override
    public List<LottoWinningInfo> findAll() {
        return em.createQuery("SELECT l FROM LottoWinningInfo l", LottoWinningInfo.class)
                .getResultList();
    }
}
