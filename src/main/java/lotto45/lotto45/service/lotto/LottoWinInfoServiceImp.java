package lotto45.lotto45.service.lotto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;
import lotto45.lotto45.repository.lotto.ILottoWinInfoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LottoWinInfoServiceImp implements ILottoWinInfoService {

    private final ILottoWinInfoRepository lottoWinInfoRepository;

    @Override
    @Transactional
    public void save(List<LottoWinningInfo> winningInfos) {
        lottoWinInfoRepository.saveAll(winningInfos);
    }

    @Override
    public int getRounds() {
        Optional<LottoWinningInfo> optionalLottoWinningInfo = this.lottoWinInfoRepository.findByLatestRounds();
        LottoWinningInfo lottoWinningInfo = optionalLottoWinningInfo.orElse(null);

        if (lottoWinningInfo == null) {
            return 0;
        }
        return lottoWinningInfo.getDrwNo();
    }

    @Override
    public List<LottoWinningInfo> findAll() {
        return this.lottoWinInfoRepository.findAll();
    }
}
