package lotto45.lotto45.controller.lotto;

import lombok.RequiredArgsConstructor;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.service.lotto.ILottoNumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LottoNumberController {

    private final ILottoNumberService ILottoNumberService;

    @GetMapping("/lotto45")
    public String newLottoNumber(Model model){
        Lotto lotto = ILottoNumberService.create();
        List<Lotto> last8Numbers = ILottoNumberService.lastLottoNumber8();
        model.addAttribute("lotto", lotto);
        model.addAttribute("last8Numbers",last8Numbers);
        return "lotto/lottoNumber";
    }

}
