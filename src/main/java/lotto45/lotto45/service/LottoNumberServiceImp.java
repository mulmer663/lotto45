package lotto45.lotto45.service;

import lombok.Getter;
import lotto45.lotto45.domain.Lotto;
import lotto45.lotto45.repository.LottoNumberRepository;
import lotto45.lotto45.repository.MemoryLottoNumberRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

@Service
public class LottoNumberServiceImp implements LottoNumberService {

    private final LottoNumberArrayList lottoNumberArrayList;
    private final LottoNumberRepository memoryLottoNumberRepository;

    @Autowired
    public LottoNumberServiceImp() {
        this.memoryLottoNumberRepository = new MemoryLottoNumberRepositoryImp();
        this.lottoNumberArrayList = new LottoNumberArrayList();
    }

    @Autowired
    public LottoNumberArrayList getLottoNumberArrayList() {
        return lottoNumberArrayList;
    }

    @Override
    public Lotto create() {
        List<Integer> tempNumber = this.lottoNumberArrayList.sixLottoNum();

        return new Lotto(tempNumber);

    }

    @Override
    public void save(Lotto lotto) {

    }

    @Override
    public void findLotto(Lotto lottoID) {

    }

    @Override
    public void findLotto(Lotto lottoNumber1, Lotto lottoNumber2, Lotto lottoNumber3, Lotto lottoNumber4, Lotto lottoNumber5, Lotto lottoNumber6) {

    }

    @Override
    public Queue<Lotto> lastLottoNumber8() {
        return memoryLottoNumberRepository.lastLottoNumber8();
    }
}
