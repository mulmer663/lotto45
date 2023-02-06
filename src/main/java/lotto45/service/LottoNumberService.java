package lotto45.service;

import lotto45.domain.Lotto;

import java.util.List;

public interface LottoNumberService {

    Lotto create();
    List<Lotto> findByRounds(int rounds);
    List<Lotto> findAll();
    List<Lotto> lastLottoNumber8();

}
