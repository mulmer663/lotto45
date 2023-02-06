package lotto45.lotto45.repository;

import lotto45.lotto45.domain.Lotto;

import java.util.List;
import java.util.Queue;

public interface LottoNumberRepository {
    void save(Lotto lotto);
    Lotto findLottoId(Lotto lotto);
    Lotto findRound(Lotto lotto);
    Queue<Lotto> lastLottoNumber8();
    List<Lotto> findAll();
}
