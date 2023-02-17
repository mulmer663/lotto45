package lotto45.lotto45.service.lotto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.repository.lotto.INonMemberLottoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

@Slf4j
@Service
@RequiredArgsConstructor
public class NomMemberLottoImp implements INonMemberLottoService {

    private final INonMemberLottoRepository nonMemberLottoRepository;

    @Override
    public Lotto create() {
        Lotto lotto = new Lotto();
//        log.info("nonMemberLottoRepository = {}", nonMemberLottoRepository);
        this.nonMemberLottoRepository.save(lotto);

        return lotto;
    }

    @Override
    public List<Lotto> lastLottoNumber8() {
        List<Lotto> lottoList = new ArrayList<>();
        Deque<Lotto> lottoQueue = nonMemberLottoRepository.lastLottoNumber8();

        for (Lotto lotto : lottoQueue) {
            lottoList.add(0, lotto);
        }

        return lottoList;
    }
}
