package lotto45.lotto45.controller.lotto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;
import lotto45.lotto45.service.lotto.ILottoWinInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LottoWinInfoController {

    private final ILottoWinInfoService lottoWinInfoService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String LOTTO_API_URL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo={rounds}";

    /**
     * DB에 저장된 가장 최신 회차 정보를 보여줌
     */
    @GetMapping("/save-lotto_win_info")
    public String lottoWinInfo(Model model) {

        Lotto lotto = Lotto.createLotto();
        int currentRounds = lotto.getRounds() - 1;
        int rounds = this.lottoWinInfoService.getRounds();

        model.addAttribute("currentRounds", currentRounds);
        model.addAttribute("rounds", rounds);

        return "lotto/lottoWinInfoForm";
    }

    /**
     * 버튼 누르면 회차 데이터 쭉 뽑아서 겹치지 않는 부분만 저장하는 형식 --> 서비스와 연결
     */
    @PostMapping("/save-lotto_win_info")
    public String saveLottoWinInfo(Integer rounds) throws IOException {

//        log.info("rounds = {}", rounds);
        List<LottoWinningInfo> winningInfos = new ArrayList<>();
        objectMapper.registerModule(new JavaTimeModule());
        RestTemplate restTemplate = new RestTemplate();
        int savedRounds = this.lottoWinInfoService.getRounds();

        for (int currentRounds = savedRounds + 1; currentRounds <= rounds; currentRounds++) {
            String messageBody = restTemplate.getForObject
                    (LOTTO_API_URL, String.class, currentRounds);
            LottoWinningInfo winInfo = objectMapper.readValue(messageBody, LottoWinningInfo.class);
            winningInfos.add(winInfo);
        }

        this.lottoWinInfoService.save(winningInfos);

//        log.info("winInfo = {}", winInfo);
        return "redirect:/";
    }

}
