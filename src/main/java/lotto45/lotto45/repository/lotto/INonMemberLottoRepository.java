package lotto45.lotto45.repository.lotto;

import lotto45.lotto45.domain.lotto.Lotto;

import java.util.Deque;

public interface INonMemberLottoRepository {
    void save(Lotto lotto);
    Deque<Lotto> lastLottoNumber8();
}
