package lotto45.lotto45.controller;

import lombok.RequiredArgsConstructor;
import lotto45.lotto45.domain.Lotto;
import lotto45.lotto45.service.LottoNumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LottoNumberController {
//
//    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//    LottoNumberService lottoNumberService = ac.getBean("lottoNumberService", LottoNumberService.class);
//    LottoNumberArrayList lottoNumberArrayList = ac.getBean("lottoNumberArrayList",LottoNumberArrayList.class);

    private final LottoNumberService lottoNumberService;

    @GetMapping("/lotto45")
    public String newLottoNumber(Model model){
        Lotto lotto = lottoNumberService.create();
        model.addAttribute("lotto", lotto);
        return "lotto45main";
    }

}
