package lotto45.lotto45.service.lotto;

import lotto45.lotto45.repository.lotto.ILottoWinInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WinSttServiceImpTest {

    @Autowired
    ILottoWinInfoRepository lottoWinInfoRepository;
    @Autowired
    IWinSttService winSttService;

//    @Test
//    public void calculateSttInfoTest() throws Exception {
//        // * given
//        List<Integer> lottoNums = new ArrayList<>();
//        lottoNums.add(14);
//        lottoNums.add(19);
//        lottoNums.add(28);
//        lottoNums.add(30);
//        lottoNums.add(45);
//        lottoNums.add(33);
//
//        // * when
//        List<WinSttDTO> winSttDTOS = winSttService.calculateSttInfo(lottoNums);
//
//        // * then
//        assertThat(winSttDTOS.size()).isEqualTo(1);
//        for (WinSttDTO winSttDTO : winSttDTOS) {
//            assertThat(winSttDTO.getRank()).isEqualTo(2);
//            assertThat(winSttDTO.getBonusNum()).isEqualTo(33);
//            assertThat(winSttDTO.getRounds()).isEqualTo(1054);
//            assertThat(winSttDTO.getLottoNums().size()).isEqualTo(5);
//            System.out.println("winSttInfoDTO = " + winSttDTO);
//        }
//
//    }
}