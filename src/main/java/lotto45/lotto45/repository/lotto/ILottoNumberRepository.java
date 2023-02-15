package lotto45.lotto45.repository.lotto;

import lotto45.lotto45.domain.lotto.Lotto;

import java.util.List;
import java.util.Queue;

public interface ILottoNumberRepository {
    void save(Lotto lotto);
    List<Lotto> findByRounds(int rounds);
    Queue<Lotto> lastLottoNumber8();
    List<Lotto> findAll();
}
