package lotto45.lotto45.service;

import lotto45.lotto45.domain.Lotto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LottoNumberServiceImp implements LottoNumberService{

    private final LottoNumberArrayList lottoNumberArrayList = new LottoNumberArrayList();

    public LottoNumberArrayList getLottoNumberArrayList() {
        return lottoNumberArrayList;
    }

    @Override
    public Lotto create() {
        this.lottoNumberArrayList.shuffle();
        List<Integer> tempNumber = this.lottoNumberArrayList.sixLottoNum();
        return new Lotto(tempNumber.get(0),tempNumber.get(1),tempNumber.get(2),tempNumber.get(3),tempNumber.get(4),tempNumber.get(5));
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
    public List<Integer> lastLottoNumber8() {
        return null;
    }
}
