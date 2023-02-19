package lotto45.lotto45.service.lotto;

import lotto45.lotto45.domain.lotto.Lotto;

import java.util.List;

public interface INonMemberLottoService {
    Lotto create();
    List<Lotto> lastLottoNumber8();
}
