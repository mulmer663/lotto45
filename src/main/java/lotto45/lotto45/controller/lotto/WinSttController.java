package lotto45.lotto45.controller.lotto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.service.lotto.IMemberLottoService;
import lotto45.lotto45.service.lotto.IWinSttService;
import lotto45.lotto45.service.lotto.WinSttDTO;
import lotto45.lotto45.web.argumentresolver.Login;
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
    private final IMemberLottoService memberLottoService;

    /**
     * <p>LottoWin 정보를 전부 다 빼내어서 비교해 어느 회차의 1~3위 번호였는지 정보를 표시해주는 컨트롤러</p>
     * <p>검증으로는 6개의 숫자 1~ 45인 범위 + 6개를 set에 집어넣었을 때 크기가 6이여야하는 검증 조건이 있고</p>
     * <p>오직 로그인한 회원만 해당 기능을 사용하게 만듬</p>
     * <p>따라서 로그인 후 해당 컨트롤러에 접근할 수 있고 1~3위 로직은 따로 서비스로 구현</p>
     */
    @GetMapping("/WinSttCheck")
    public String saveWinSttForm(@Login Member member,
                                 Model model) {

        List<Lotto> lottoList = this.memberLottoService.findAll(member.getId());
        for (Lotto lotto : lottoList) {
            lotto.setBookmark(true);
        }
        LottoMemberForm form = new LottoMemberForm(null, lottoList);
        model.addAttribute("form", form);
        model.addAttribute("member", member);

        return "lotto/winSttForm";
    }

    @PostMapping("/WinSttCheck")
    public String checkLottoNumber(@ModelAttribute("form") LottoMemberForm form,
                                   @Login Member member,
                                   Model model) {

        List<Lotto> bookmarkList = form.getLottoList();
        List<Lotto> savedLottoList = this.memberLottoService.findAll(member.getId());
        for (int i = 0; i < bookmarkList.size(); i++) {
            savedLottoList.get(i).setBookmark(bookmarkList.get(i).isBookmark());
        }

        List<WinSttDTO> winSttDTOS = this.winSttService.calculateSttInfo(member.getId(), savedLottoList);

        if (winSttDTOS.size() == 0) {
            model.addAttribute("noWinStt", "당첨 통계가 없습니다.");
        } else {
            model.addAttribute("winSttS", winSttDTOS);
        }

        return "lotto/winStt";
    }
}
