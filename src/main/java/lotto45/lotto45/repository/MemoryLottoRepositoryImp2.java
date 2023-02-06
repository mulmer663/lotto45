package lotto45.lotto45.repository;

import lotto45.lotto45.domain.Lotto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryLottoRepositoryImp2 implements LottoNumberRepository {

    private static final Map<Long, Lotto> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(Lotto lotto) {
        lotto.setId(++sequence);
        store.put(lotto.getId(), lotto);
    }

    @Override
    public List<Lotto> findRound(int rounds) {
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
        Queue<Lotto> lottoQueue = new LinkedList<>();

        for (Long key : store.keySet()) {
            if (key <= 8) {
                lottoQueue.add(store.get(key));
            } else {
                break;
            }
        }

        return lottoQueue;
    }

    @Override
    public List<Lotto> findAll() {
        return new ArrayList<>(store.values());
    }
}
