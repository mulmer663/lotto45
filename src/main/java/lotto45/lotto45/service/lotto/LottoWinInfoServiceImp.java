package lotto45.lotto45.service.lotto;

import lombok.RequiredArgsConstructor;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;
import lotto45.lotto45.repository.lotto.ILottoWinInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LottoWinInfoServiceImp implements ILottoWinInfoService {

    private final ILottoWinInfoRepository lottoWinInfoRepository;

    @Override
    public void save(List<LottoWinningInfo> winningInfos) {
        List<LottoWinningInfo> savedList = this.lottoWinInfoRepository.findAll();

        for (LottoWinningInfo lottoWinningInfo : savedList) {
            winningInfos.remove(lottoWinningInfo);
        }

        lottoWinInfoRepository.saveAll(winningInfos);
    }

    @Override
    public int getRounds() {
        Optional<LottoWinningInfo> optionalLottoWinningInfo = this.lottoWinInfoRepository.findByLatestRounds();
        LottoWinningInfo lottoWinningInfo = optionalLottoWinningInfo.get();
        return lottoWinningInfo.getDrwNo();
    }

    @Override
    public List<LottoWinningInfo> findAll() {
        return this.lottoWinInfoRepository.findAll();
    }
}
