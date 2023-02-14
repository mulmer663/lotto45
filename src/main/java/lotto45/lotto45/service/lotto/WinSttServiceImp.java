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
    public List<WinSttInfoDTO> calculateSttInfo(List<Integer> lottoNums) {

        List<WinSttInfoDTO> winSttInfoDTOList = new ArrayList<>();
        List<LottoWinningInfo> lottoWinInfos = this.lottoWinInfoRepository.findAll();

        for (LottoWinningInfo lottoWinInfo : lottoWinInfos) {

            WinSttInfoDTO winSttInfoDTO = new WinSttInfoDTO();
            List<Integer> winningLottoNums = this.extractedWinningLottoNums(lottoWinInfo);
            int bonusNo = lottoWinInfo.getBnusNo();
            int count = 0;

            for (int num : lottoNums) {
                if (winningLottoNums.contains(num)) {
                    winSttInfoDTO.getLottoNums().add(num);
                    count++;
                }
            }
            boolean isHasBonusNo = lottoNums.contains(bonusNo);

            if (count == 6) {
                winSttInfoDTO.setRank(1);
                winSttInfoDTO.setRounds(lottoWinInfo.getDrwNo());
                winSttInfoDTO.setWinAmount(lottoWinInfo.getFirstWinamnt());
                winSttInfoDTOList.add(winSttInfoDTO);
            } else if (count == 5 && isHasBonusNo) {
                winSttInfoDTO.setRank(2);
                winSttInfoDTO.setRounds(lottoWinInfo.getDrwNo());
                winSttInfoDTO.setBonusNum(bonusNo);
                winSttInfoDTOList.add(winSttInfoDTO);
            } else if (count == 5 && !isHasBonusNo) {
                winSttInfoDTO.setRank(3);
                winSttInfoDTO.setRounds(lottoWinInfo.getDrwNo());
                winSttInfoDTOList.add(winSttInfoDTO);
            }
        }

        return winSttInfoDTOList;
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
