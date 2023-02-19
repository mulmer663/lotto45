package lotto45.lotto45.controller.lotto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.lotto.LottoWinningInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
@Slf4j
public class SearchAPIController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ResponseBody
    @GetMapping("/testJson")
    public String testJson(@RequestBody String messageBody) throws IOException {

        objectMapper.registerModule(new JavaTimeModule());
        log.info("messageBody = {}", messageBody);
        LottoWinningInfo winInfo = objectMapper.readValue(messageBody, LottoWinningInfo.class);
        log.info("winInfo = {}", winInfo);

        return "ok";
    }

    /**
     * <p>지금 단순히 Get 방식으로 조회했을 때에는 현재 회차 직전의 당첨 정보를 출력하도록 한다.</p>
     *
     * <p>1. objectMapper가 LocalDate를 직렬화 할 수 있도록 해당 문구 추가</p>
     * <p>2. RestTemplate로 외부 URL에 Get 방식으로 요청을 보내고 응답을 받게함</p>
     * <p>3. getForObject로 하면 getForEntity와 getBody를 합친 결과물을 얻을 수 있음</p>
     * <p>4. 해당 응답을 바로 LottoWinningInfo로 받을 시 호환이 안된다고 오류가 뜨고 해결방법이 만만치 않으므로</p>
     * <p>5. String으로 받아 Json 형식의 문자열로 기록하고 해당 문자열을 objectMapper를 사용해서 객체로 변환</p>
     * @param rounds 회차 정보
     * @return 해당 회차 당첨 정보를 가진 모듈을 View에 전달
     */
    @GetMapping("/winningInfo")
    public String lottoWinningInfo(Integer rounds, Model model) throws IOException {

        Lotto lotto = Lotto.createLotto();
        rounds = lotto.getRounds() - 1;

        objectMapper.registerModule(new JavaTimeModule());
        RestTemplate restTemplate = new RestTemplate();

        String messageBody = restTemplate.getForObject
                ("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo={rounds}",
                        String.class,
                        rounds);
        LottoWinningInfo winInfo = objectMapper.readValue(messageBody, LottoWinningInfo.class);
        winInfo.makeColorList();
//        log.info("winInfo = {}", winInfo);

        model.addAttribute("winInfo", winInfo);
        model.addAttribute("rounds", rounds);
        return "lotto/lottoWinInfo";
    }

    @PostMapping("/winningInfo")
    public String getLottoWinningInfo(Integer rounds, Model model) throws IOException {

        log.info("rounds = {}", rounds);
        objectMapper.registerModule(new JavaTimeModule());
        RestTemplate restTemplate = new RestTemplate();

        String messageBody = restTemplate.getForObject
                ("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo={rounds}",
                        String.class,
                        rounds);
        LottoWinningInfo winInfo = objectMapper.readValue(messageBody, LottoWinningInfo.class);
        winInfo.makeColorList();
//        log.info("winInfo = {}", winInfo);

        model.addAttribute("winInfo", winInfo);
        return "lotto/lottoWinInfo";
    }
}
