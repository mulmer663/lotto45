package lotto45.lotto45.repository.lotto;

import lotto45.lotto45.domain.lotto.Lotto;

import java.util.List;

public interface ILottoWinInfoRepository {

    void saveAll();
    List<Lotto> findByRounds(int rounds);
    List<Lotto> findAll();
}
