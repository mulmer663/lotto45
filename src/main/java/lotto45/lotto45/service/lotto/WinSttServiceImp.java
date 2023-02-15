package lotto45.lotto45.service.lotto;

import lombok.RequiredArgsConstructor;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;
import lotto45.lotto45.repository.lotto.ILottoWinInfoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WinSttServiceImp implements IWinSttService {

    private final ILottoWinInfoRepository lottoWinInfoRepository;

    /**
     * 리포지토리에서 저장된 모든 당첨 정보를 꺼낸 후 매개변수 로또 정보와 비교해서 당첨 통계를 만드는 메서드
     */
    @Override
    public List<WinSttDTO> calculateSttInfo(List<Integer> lottoNums) {

        List<WinSttDTO> winSttDTOList = new ArrayList<>();
        List<LottoWinningInfo> lottoWinInfos = this.lottoWinInfoRepository.findAll();

        for (LottoWinningInfo lottoWinInfo : lottoWinInfos) {

            WinSttDTO winSttDTO = new WinSttDTO();
            List<Integer> winningLottoNums = this.extractedWinningLottoNums(lottoWinInfo);
            int bonusNo = lottoWinInfo.getBnusNo();
            int count = 0;

            for (int num : lottoNums) {
                if (winningLottoNums.contains(num)) {
                    winSttDTO.getLottoNums().add(num);
                    count++;
                }
            }
            boolean isHasBonusNo = lottoNums.contains(bonusNo);

            if (count == 6) {
                winSttDTO.setRank(1);
                winSttDTO.setRounds(lottoWinInfo.getDrwNo());
                winSttDTO.setWinAmount(lottoWinInfo.getFirstWinamnt());
                winSttDTOList.add(winSttDTO);
            } else if (count == 5 && isHasBonusNo) {
                winSttDTO.setRank(2);
                winSttDTO.setRounds(lottoWinInfo.getDrwNo());
                winSttDTO.setBonusNum(bonusNo);
                winSttDTOList.add(winSttDTO);
            } else if (count == 5 && !isHasBonusNo) {
                winSttDTO.setRank(3);
                winSttDTO.setRounds(lottoWinInfo.getDrwNo());
                winSttDTOList.add(winSttDTO);
            }
        }

        return winSttDTOList;
    }

    private List<Integer> extractedWinningLottoNums(LottoWinningInfo lottoWinInfo) {
        List<Integer> winningLottoNums = new ArrayList<>();
        winningLottoNums.add(lottoWinInfo.getDrwtNo1());
        winningLottoNums.add(lottoWinInfo.getDrwtNo2());
        winningLottoNums.add(lottoWinInfo.getDrwtNo3());
        winningLottoNums.add(lottoWinInfo.getDrwtNo4());
        winningLottoNums.add(lottoWinInfo.getDrwtNo5());
        winningLottoNums.add(lottoWinInfo.getDrwtNo6());

        return winningLottoNums;
    }
}
