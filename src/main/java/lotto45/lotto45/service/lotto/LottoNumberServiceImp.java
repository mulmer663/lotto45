package lotto45.lotto45.service.lotto;

import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.repository.lotto.ILottoNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LottoNumberServiceImp implements ILottoNumberService {

    private final ILottoNumberRepository ILottoNumberRepository;

    @Autowired
    public LottoNumberServiceImp(ILottoNumberRepository ILottoNumberRepository) {
        this.ILottoNumberRepository = ILottoNumberRepository;
    }

    @Override
    public Lotto create() {
        Lotto lotto = new Lotto();
        ILottoNumberRepository.save(lotto);
        return lotto;
    }

    @Override
    public List<Lotto> findByRounds(int rounds) {
        return ILottoNumberRepository.findByRounds(rounds);
    }

    @Override
    public List<Lotto> findAll() {
        return ILottoNumberRepository.findAll();
    }

    @Override
    public List<Lotto> lastLottoNumber8() {
        List<Lotto> lottoList = new ArrayList<Lotto>();
        Queue<Lotto> lottoQueue = ILottoNumberRepository.lastLottoNumber8();

        for (Lotto lotto : lottoQueue) {
            lottoList.add(0, lotto);
        }

        return lottoList;
    }
}
