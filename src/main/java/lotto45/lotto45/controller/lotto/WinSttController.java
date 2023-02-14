package lotto45.lotto45.controller.lotto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.service.lotto.IWinSttService;
import lotto45.lotto45.service.lotto.WinSttInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WinSttController {

    private final IWinSttService winSttService;

    /**
     * <p>LottoWin 정보를 전부 다 빼내어서 비교해 어느 회차의 1~3위 번호였는지 정보를 표시해주는 컨트롤러</p>
     * <p>검증으로는 6개의 숫자 1~ 45인 범위 + 6개를 set에 집어넣었을 때 크기가 6이여야하는 검증 조건이 있고</p>
     * <p>오직 로그인한 회원만 해당 기능을 사용하게 만듬</p>
     * <p>따라서 로그인 후 해당 컨트롤러에 접근할 수 있고 1~3위 로직은 따로 서비스로 구현</p>
     */
    @GetMapping("/WinSttCheck")
    public String saveWinSttForm(@ModelAttribute("form") LottoNumsSaveForm form) {
        return "lotto/saveWinSttForm";
    }

    @PostMapping("/WinSttCheck")
    public String checkLottoNumber(@ModelAttribute("form") @Validated LottoNumsSaveForm form,
                                   BindingResult bindingResult,
                                   Model model) {

        // * 6개의 숫자는 서로 달라야하는 objectValidation 작성
        Set<Integer> set = new HashSet<>();
        set.add(form.getNum1());
        set.add(form.getNum2());
        set.add(form.getNum3());
        set.add(form.getNum4());
        set.add(form.getNum5());
        set.add(form.getNum6());

        if (set.size() != 6) {
            bindingResult.reject("notUniqueNumber", new Object[]{6}, "서로 다른 6개의 숫자여야 합니다.");
        }

        // # 실패시 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
//            log.info("errors = {}", bindingResult);
            return "lotto/saveWinSttForm";
        }


        List<Integer> lottoNums = new ArrayList<>();
        lottoNums.add(form.getNum1());
        lottoNums.add(form.getNum2());
        lottoNums.add(form.getNum3());
        lottoNums.add(form.getNum4());
        lottoNums.add(form.getNum5());
        lottoNums.add(form.getNum6());

        List<WinSttInfoDTO> winSttInfoDTOS = this.winSttService.calculateSttInfo(lottoNums);

        if (winSttInfoDTOS.size() == 0) {
            model.addAttribute("noWinStt", "당첨 통계가 없습니다.");
        } else {
            model.addAttribute("winSttS", winSttInfoDTOS);
        }

        return "lotto/checkWinStt";
    }
}
