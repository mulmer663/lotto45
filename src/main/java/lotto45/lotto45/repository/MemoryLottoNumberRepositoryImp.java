package lotto45.lotto45.repository;

import lotto45.lotto45.domain.Lotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryLottoNumberRepositoryImp implements LottoNumberRepository {

    private final Map<Long, Lotto> memoryRepository;
    private final Queue<Lotto> memoryLastLottoNumberRepository;

    @Autowired
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
    public List<Lotto> findRound(int rounds) {
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
