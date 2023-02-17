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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LottoNumberController {

    private final ILottoNumberService lottoNumberService;
    private final INonMemberLottoService nonMemberLottoService;

    @GetMapping("/lotto45Plus")
    public String memberLottoNumber(@Login Member member, Model model) {

        Lotto lotto = lottoNumberService.create();
        List<Lotto> last8Numbers = lottoNumberService.lastLottoNumber8();

        model.addAttribute("lotto", lotto);
        model.addAttribute("last8Numbers", last8Numbers);
        return "lotto/lottoNumberPlus";
    }

    @PostMapping("/lotto45Plus")
    public String memberBookMarKLotto(@RequestParam(value = "bookmark1", required = false) boolean bookmark1,
                                      @RequestParam(value = "bookmark2", required = false) boolean bookmark2,
                                      @RequestParam(value = "bookmark3", required = false) boolean bookmark3,
                                      @RequestParam(value = "bookmark4", required = false) boolean bookmark4,
                                      @RequestParam(value = "bookmark5", required = false) boolean bookmark5,
                                      @RequestParam(value = "bookmark6", required = false) boolean bookmark6,
                                      @RequestParam(value = "bookmark7", required = false) boolean bookmark7,
                                      @RequestParam(value = "bookmark8", required = false) boolean bookmark8,
                                      @Login Member member,
                                      Model model) {

        log.info("member = {}", member);

        List<Lotto> last8Numbers = this.lottoNumberService.lastLottoNumber8();

        if (bookmark1) last8Numbers.get(0).setBookmark(true);
        if (bookmark2) last8Numbers.get(1).setBookmark(true);
        if (bookmark3) last8Numbers.get(2).setBookmark(true);
        if (bookmark4) last8Numbers.get(3).setBookmark(true);
        if (bookmark5) last8Numbers.get(4).setBookmark(true);
        if (bookmark6) last8Numbers.get(5).setBookmark(true);
        if (bookmark7) last8Numbers.get(6).setBookmark(true);
        if (bookmark8) last8Numbers.get(7).setBookmark(true);

        log.info("last8Numbers = {}", last8Numbers);
        this.lottoNumberService.saveBookMarkedLotto(last8Numbers, member);

        model.addAttribute("last8Numbers", last8Numbers);

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
