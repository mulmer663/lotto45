package lotto45.lotto45.service.lotto;

import lotto45.lotto45.domain.lotto.Lotto;

import java.util.List;

public interface LottoNumberService {

    Lotto create();
    List<Lotto> findByRounds(int rounds);
    List<Lotto> findAll();
    List<Lotto> lastLottoNumber8();

}
