package lotto45.lotto45.repository.lotto;

import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import java.util.ArrayDeque;
import java.util.Deque;

@Slf4j
@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MemoryNonMemberLottoRepositoryImp implements INonMemberLottoRepository {

    private final Deque<Lotto> lottoQueue = new ArrayDeque<>();
    private long sequence = 0L;

    @Override
    public void save(Lotto lotto) {

        lotto.setId(++sequence);

        if (lottoQueue.size() < 8) {
            lottoQueue.add(lotto);
        } else if (lottoQueue.size() == 8) {
            lottoQueue.poll();
            lottoQueue.add(lotto);
        }
    }

    @Override
    public Deque<Lotto> lastLottoNumber8() {
        return lottoQueue;
    }
}
