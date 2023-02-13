package lotto45.lotto45.controller.lotto;

import lombok.RequiredArgsConstructor;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.service.lotto.LottoNumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LottoNumberController {

    private final LottoNumberService lottoNumberService;

    @GetMapping("/lotto45")
    public String newLottoNumber(Model model){
        Lotto lotto = lottoNumberService.create();
        List<Lotto> last8Numbers = lottoNumberService.lastLottoNumber8();
        model.addAttribute("lotto", lotto);
        model.addAttribute("last8Numbers",last8Numbers);
        return "lotto/lotto45main";
    }

    /**
     * LottoWin 정보를 전부 다 빼내어서 비교해 어느 회차의 1~3위 번호였는지 정보를 표시해주는 컨트롤러
     * 검증으로는 6개의 숫자 1~ 45인 범위 + 6개를 set에 집어넣었을 때 크기가 6이여야하는 검증 조건이 있고
     * 오직 로그인한 회원만 해당 기능을 사용하게 만듬
     * 따라서 로그인 후 해당 컨트롤러에 접근할 수 있고 1~3위 로직은 따로 서비스로 구현
     */
    @GetMapping("/lottoCheck")
    public String checkLottoNumber() {
        return null;
    }
}
