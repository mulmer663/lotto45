package lotto45.lotto45.service;

import lotto45.lotto45.domain.Lotto;

import java.util.List;

public interface LottoNumberService {

    Lotto create();

    void save(Lotto lotto);

    void findLotto(Lotto lottoID);

    void findLotto(Lotto lottoNumber1, Lotto lottoNumber2, Lotto lottoNumber3, Lotto lottoNumber4, Lotto lottoNumber5, Lotto lottoNumber6);

    List<Integer> lastLottoNumber8();

}
