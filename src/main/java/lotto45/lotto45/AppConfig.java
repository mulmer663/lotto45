package lotto45.lotto45;


import lotto45.lotto45.lottoNumber.LottoNumberArrayList;
import lotto45.lotto45.lottoNumber.LottoNumberService;
import lotto45.lotto45.lottoNumber.LottoNumberServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public LottoNumberService lottoNumberService(){
        return new LottoNumberServiceImp();
    }

    @Bean
    public LottoNumberArrayList lottoNumberArrayList(){
        return new LottoNumberArrayList();
    }

}
