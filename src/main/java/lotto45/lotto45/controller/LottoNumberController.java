package lotto45.lotto45.controller;

import lotto45.lotto45.AppConfig;
import lotto45.lotto45.lottoNumber.Lotto;
import lotto45.lotto45.lottoNumber.LottoNumberArrayList;
import lotto45.lotto45.lottoNumber.LottoNumberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LottoNumberController {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    LottoNumberService lottoNumberService = ac.getBean("lottoNumberService", LottoNumberService.class);
    LottoNumberArrayList lottoNumberArrayList = ac.getBean("lottoNumberArrayList",LottoNumberArrayList.class);

    @GetMapping("/lotto45")
    public String newLottoNumber(Model model){
        lottoNumberArrayList.shuffle();
        Lotto lotto = lottoNumberService.create(lottoNumberArrayList);
        model.addAttribute("lotto",lotto);
        return "lotto45main";
    }

}
