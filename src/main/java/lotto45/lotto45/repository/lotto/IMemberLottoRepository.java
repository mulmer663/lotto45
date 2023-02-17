package lotto45.lotto45.repository.lotto;

import lotto45.lotto45.domain.lotto.Lotto;

import java.util.List;
import java.util.Queue;

public interface IMemberLottoRepository {
    void save(List<Lotto> lottoList, long memberId);
    List<Lotto> findByMemberId(long memberId);
    void delete(List<Lotto> lottoList);
}
