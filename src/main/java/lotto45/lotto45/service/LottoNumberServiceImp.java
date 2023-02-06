package lotto45.lotto45.service;

import lotto45.lotto45.domain.Lotto;
import lotto45.lotto45.domain.LottoNumberArrayList;
import lotto45.lotto45.repository.LottoNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LottoNumberServiceImp implements LottoNumberService {

    private final LottoNumberRepository lottoNumberRepository;

    @Autowired
    public LottoNumberServiceImp(LottoNumberRepository lottoNumberRepository) {
        this.lottoNumberRepository = lottoNumberRepository;
    }


    @Override
    public Lotto create() {
        LottoNumberArrayList lottoNumberArrayList = new LottoNumberArrayList();
        List<Integer> tempNumber = lottoNumberArrayList.sixLottoNum();
        Lotto lotto = new Lotto(tempNumber);
        lottoNumberRepository.save(lotto);
        return lotto;
    }



    @Override
    public void findLotto(Lotto lottoNumber1, Lotto lottoNumber2, Lotto lottoNumber3, Lotto lottoNumber4, Lotto lottoNumber5, Lotto lottoNumber6) {

    }

    @Override
    public List<Lotto> lastLottoNumber8() {
        return new ArrayList<Lotto>(lottoNumberRepository.lastLottoNumber8());
    }
}
