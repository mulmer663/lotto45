package lotto45.lotto45.controller.lotto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;
import lotto45.lotto45.repository.lotto.ILottoWinInfoRepository;
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
public class LottoWinningSaveController {

    private final ILottoWinInfoRepository lottoWinInfoRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String LOTTO_API_URL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo={rounds}";

    /**
     * 단순히 현재 당첨 회차를 전달함
     */
    @GetMapping("/save-lotto_win_info")
    public String lottoWinInfo(Model model) {
        Lotto lotto = new Lotto();
        Integer rounds = lotto.getRounds() - 1;
        model.addAttribute("rounds", rounds);

        return "/lotto/saveLottoWinInfo";
    }

    /**
     * 버튼 누르면 회차 데이터 쭉 뽑아서 저장하는 형식으로
     * 간단하니깐 바로 리포지토리와 연결
     */
    @PostMapping("/save-lotto_win_info")
    public String saveLottoWinInfo(Integer rounds) throws IOException {

//        log.info("rounds = {}", rounds);
        List<LottoWinningInfo> winningInfos = new ArrayList<>();
        objectMapper.registerModule(new JavaTimeModule());
        RestTemplate restTemplate = new RestTemplate();

        for (int currentRounds = 1; currentRounds <= rounds; currentRounds++) {
            String messageBody = restTemplate.getForObject
                    (LOTTO_API_URL, String.class, currentRounds);
            LottoWinningInfo winInfo = objectMapper.readValue(messageBody, LottoWinningInfo.class);
            winningInfos.add(winInfo);
        }
        this.lottoWinInfoRepository.saveAll(winningInfos);

//        log.info("winInfo = {}", winInfo);
        return "redirect:/";
    }

}
