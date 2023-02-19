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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

        LottoMemberForm form = new LottoMemberForm(lotto, last8Numbers);

//        for (Lotto lottoNum : last8Numbers) {
//            log.info("lottoNum.isBookmark() = {}", lottoNum.isBookmark());
//        }
        model.addAttribute("form", form);
        model.addAttribute("member", member);

        return "lotto/lottoNumberPlus";
    }

    @PostMapping("/lotto45Plus")
    public String memberBookMarKLotto(@ModelAttribute("form") LottoMemberForm form,
                                      @Login Member member) {

        List<Lotto> formLast8Numbers = form.getLottoList();
        List<Lotto> lastLottoNumber8 = this.lottoNumberService.lastLottoNumber8();

        int count = 0;
        for (int i = 0; i < formLast8Numbers.size(); i++) {
            lastLottoNumber8.get(i).setBookmark(formLast8Numbers.get(i).isBookmark());
//            log.info("lotto.isBookmark(){} = {}", count++, formLast8Numbers.get(i).isBookmark());
        }

        this.lottoNumberService.saveBookMarkedLotto(lastLottoNumber8, member);

        return "redirect:/lotto45Plus";
    }

    @GetMapping("/lotto45Plus/{memberId}")
    public String memberBookMarkList(@PathVariable long memberId, @Login Member member, Model model) {

        if (member.getId() != memberId) {
            throw new RuntimeException("로그인한 맴버와 접속한 페이지의 id가 일치하지 않습니다.");
        }

        List<Lotto> bookmarks = this.lottoNumberService.findAll(memberId);
        for (Lotto lotto : bookmarks) {
            lotto.setBookmark(true);
        }

        LottoMemberForm form = new LottoMemberForm(null, bookmarks);
        model.addAttribute("form", form);
        model.addAttribute("member", member);

        return "lotto/bookmarks";
    }

    @PostMapping("/lotto45Plus/{memberId}")
    public String editMemberBookMarkList(@PathVariable long memberId,
                                         @ModelAttribute("form") LottoMemberForm form) {

        List<Lotto> bookmarkList = form.getLottoList();
        List<Lotto> savedBookmarkList = this.lottoNumberService.findAll(memberId);

        for (int i = 0; i < bookmarkList.size(); i++) {
            log.info("bookmarkList.get({}) = {}", i, bookmarkList.get(i));
            log.info("savedBookmarkList.get({}) = {}", i, savedBookmarkList.get(i));
            savedBookmarkList.get(i).setBookmark(bookmarkList.get(i).isBookmark());
        }

        this.lottoNumberService.removeUnBookMarkedLotto(savedBookmarkList, memberId);

        return "redirect:/lotto45Plus/" + memberId;
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
