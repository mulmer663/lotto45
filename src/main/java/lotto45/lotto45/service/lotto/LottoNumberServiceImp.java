package lotto45.lotto45.service.lotto;

import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.repository.lotto.LottoNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LottoNumberServiceImp implements LottoNumberService {

    private final LottoNumberRepository lottoNumberRepository;

    @Autowired
    public LottoNumberServiceImp(LottoNumberRepository lottoNumberRepository) {
        this.lottoNumberRepository = lottoNumberRepository;
    }

    @Override
    public Lotto create() {
        Lotto lotto = new Lotto();
        lottoNumberRepository.save(lotto);
        return lotto;
    }

    @Override
    public List<Lotto> findByRounds(int rounds) {
        return lottoNumberRepository.findByRounds(rounds);
    }

    @Override
    public List<Lotto> findAll() {
        return lottoNumberRepository.findAll();
    }

    @Override
    public List<Lotto> lastLottoNumber8() {
        List<Lotto> lottoList = new ArrayList<Lotto>();
        Queue<Lotto> lottoQueue = lottoNumberRepository.lastLottoNumber8();

        for (Lotto lotto : lottoQueue) {
            lottoList.add(0, lotto);
        }

        return lottoList;
    }
}
