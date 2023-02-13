package lotto45.lotto45.repository.lotto;

import lotto45.lotto45.domain.lotto.Lotto;

import java.util.*;

//@Repository
public class MemoryLottoNumberRepositoryImp implements LottoNumberRepository {

    private final Map<Long, Lotto> memoryRepository;
    private final Queue<Lotto> memoryLastLottoNumberRepository;

    public MemoryLottoNumberRepositoryImp() {
        this.memoryRepository = new HashMap<>();
        this.memoryLastLottoNumberRepository = new LinkedList<>();
    }

    @Override
    public void save(Lotto lotto) {
        memoryRepository.put(lotto.getId(), lotto);

        if (memoryLastLottoNumberRepository.size() >= 8) {
            memoryLastLottoNumberRepository.add(lotto);
        } else {
            memoryLastLottoNumberRepository.remove();
            memoryLastLottoNumberRepository.add(lotto);
        }
    }

    @Override
    public List<Lotto> findByRounds(int rounds) {
        return null;
    }

    @Override
    public List<Lotto> findAll() {
        return null;
    }

    @Override
    public Queue<Lotto> lastLottoNumber8() {
        return memoryLastLottoNumberRepository;
    }
}
