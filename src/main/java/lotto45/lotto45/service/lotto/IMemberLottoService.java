package lotto45.lotto45.service.lotto;

import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.member.Member;

import java.util.List;

public interface IMemberLottoService {

    Lotto create();
    List<Lotto> lastLottoNumber8();
    void saveBookMarkedLotto(List<Lotto> lottoList, Member member);
    List<Lotto> findAll(long memberId);
    void removeUnBookMarkedLotto(List<Lotto> lottoList, long memberId);
}
