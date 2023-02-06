package lotto45.lotto45.service;

import lombok.Getter;
import lotto45.lotto45.domain.Lotto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LottoNumberServiceImp implements LottoNumberService{

    private final LottoNumberArrayList lottoNumberArrayList = new LottoNumberArrayList();

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
    public List<Integer> lastLottoNumber8() {
        return null;
    }
}
