package lotto45.lotto45.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Lotto {

    private long id;
    private Map<Integer, LottoColor> lottoColorMap;
    private List<Integer> lottoNumArr;
    private LocalDateTime date;
    private int turn;

    public Lotto(List<Integer> lottoNumArr) {
        this.lottoNumArr = lottoNumArr;
        this.lottoColorMap = new HashMap<>();
        this.date = LocalDateTime.now();

        if (this.lottoNumArr.size() != 6) {
            throw new RuntimeException("로또 숫자는 6개 입니다.");
        }

        for (int lottoNum : this.lottoNumArr) {

            if (lottoNum <= 10) {
                this.lottoColorMap.put(lottoNum, LottoColor.warning);
            } else if (lottoNum <= 20) {
                this.lottoColorMap.put(lottoNum, LottoColor.primary);
            } else if (lottoNum <= 30) {
                this.lottoColorMap.put(lottoNum, LottoColor.danger);
            } else if (lottoNum <= 40) {
                this.lottoColorMap.put(lottoNum, LottoColor.secondary);
            } else {
                this.lottoColorMap.put(lottoNum, LottoColor.success);
            }
        }

        // 토요일 10시 기준으로 회차 넘어간다고 하면
        // 토요일 10시 부터 다음주 토요일 10시까지가 한 회차
        // 기준 회차가 하나 필요하다 1000회의 토요일 10시를 기준으로 하자

    }
}