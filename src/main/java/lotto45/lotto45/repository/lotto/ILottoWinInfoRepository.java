package lotto45.lotto45.repository.lotto;

import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;

import java.util.List;
import java.util.Optional;

public interface ILottoWinInfoRepository {

    void saveAll(List<LottoWinningInfo> winningInfos);
    Optional<LottoWinningInfo> findByLatestRounds();
    List<LottoWinningInfo> findAll();
}
