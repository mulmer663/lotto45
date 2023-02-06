package lotto45.lotto45.repository;

import lotto45.lotto45.domain.Lotto;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryLottoRepositoryImp2 implements LottoNumberRepository {

    private static final Map<Long, Lotto> store = new HashMap<>();
    private static long sequence = 0L;
    private static final Queue<Lotto> lottoQueue = new LinkedList<>();

    @Override
    public void save(Lotto lotto) {
        lotto.setId(++sequence);

        if (lottoQueue.size() < 8) {
            lottoQueue.add(lotto);
        } else if (lottoQueue.size() == 8) {
            lottoQueue.poll();
            lottoQueue.add(lotto);
        }

        store.put(lotto.getId(), lotto);
    }

    @Override
    public List<Lotto> findByRounds(int rounds) {
        List<Lotto> lottos = new ArrayList<>();

        for (Lotto savedLotto : store.values()) {
            if (savedLotto.getRounds() == rounds) {
                lottos.add(savedLotto);
            }
        }

        return lottos;
    }

    @Override
    public Queue<Lotto> lastLottoNumber8() {
        return lottoQueue;
    }

    @Override
    public List<Lotto> findAll() {
        return new ArrayList<>(store.values());
    }
}
