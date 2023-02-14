package lotto45.lotto45.repository.lotto;

import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;

import java.util.List;

public interface ILottoWinInfoRepository {

    void saveAll(List<LottoWinningInfo> winningInfos);
    List<LottoWinningInfo> findByRounds(int rounds);
    List<LottoWinningInfo> findAll();
}
