package lotto45.lotto45.controller.lotto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.service.lotto.ILottoNumberService;
import lotto45.lotto45.service.lotto.INonMemberLottoService;
import lotto45.lotto45.web.argumentresolver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LottoNumberController {

    private final ILottoNumberService lottoNumberService;
    private final INonMemberLottoService nonMemberLottoService;

    @GetMapping("/lotto45Plus")
    public String memberLottoNumber(Model model) {

        Lotto lotto = lottoNumberService.create();
        List<Lotto> last8Numbers = lottoNumberService.lastLottoNumber8();

        LottoMemberForm form = new LottoMemberForm(lotto, last8Numbers);

//        for (Lotto lottoNum : last8Numbers) {
//            log.info("lottoNum.isBookmark() = {}", lottoNum.isBookmark());
//        }
        model.addAttribute("form", form);

        return "lotto/lottoNumberPlus";
    }

    @PostMapping("/lotto45Plus")
    public String memberBookMarKLotto(@ModelAttribute("form") LottoMemberForm form,
                                      @Login Member member) {

        List<Lotto> formLast8Numbers = form.getLast8Numbers();
        List<Lotto> savedLastLottoNumber8 = this.lottoNumberService.lastLottoNumber8();

        int count = 0;
        for (int i = 0; i < formLast8Numbers.size(); i++) {
            savedLastLottoNumber8.get(i).setBookmark(formLast8Numbers.get(i).isBookmark());
//            log.info("lotto.isBookmark(){} = {}", count++, formLast8Numbers.get(i).isBookmark());
        }

        this.lottoNumberService.saveBookMarkedLotto(formLast8Numbers, member);

        return "redirect:/lotto45Plus";
    }

    @GetMapping("/lotto45")
    public String nonMemberLottoNumber(Model model) {
        Lotto lotto = nonMemberLottoService.create();
        List<Lotto> last8Numbers = nonMemberLottoService.lastLottoNumber8();

        model.addAttribute("lotto", lotto);
        model.addAttribute("last8Numbers", last8Numbers);
        return "lotto/lottoNumber";
    }

}
