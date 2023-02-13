package lotto45.lotto45.controller.lotto;

import lotto45.lotto45.domain.lotto.Lotto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LottoWinningSaveController {

    /**
     * 이쪽으로 가면 버튼 하나 연결 해주고
     */
    @GetMapping("/save-lotto_win_info")
    public String lottoWinInfo(Model model) {
        Lotto lotto = new Lotto();
        int rounds = lotto.getRounds() - 1;
        model.addAttribute("rounds", rounds);

        return "/lotto/saveLottoWinInfo";
    }

    /**
     * 버튼 누르면 회차 데이터 쭉 뽑아서 저장하는 형식으로
     * 간단하니깐 바로 리포지토리와 연결
     */
    @PostMapping("/save-lotto_win_info")
    public String saveLottoWinInfo() {
        return null;
    }

}
