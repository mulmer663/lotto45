package lotto45.lotto45.service.lotto;

import lotto45.lotto45.domain.lotto.LottoWinningInfo;

import java.util.List;

public interface ILottoWinInfoService {

    void save(List<LottoWinningInfo> winningInfos);
    int getRounds();
    List<LottoWinningInfo> findAll();
}
