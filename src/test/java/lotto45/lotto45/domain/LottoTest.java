package lotto45.lotto45.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class LottoTest {

    LottoNumberArrayList lottoNumberArrayList = new LottoNumberArrayList();
    Lotto lotto = new Lotto(lottoNumberArrayList.sixLottoNum());

    @Test
    public void 회차테스트() throws Exception {

        // 토요일 10시 기준으로 회차 넘어간다고 하면
        // 토요일 10시 부터 다음주 토요일 10시까지가 한 회차
        // 기준 회차가 하나 필요하다 1000회의 토요일 10시를 기준으로 하자 --> 2022-01-29

        // * given
        LocalDateTime dateTime = lotto.getDateTime();
        LocalDateTime standardDateTime = LocalDateTime.of(2022, 1, 29, 22, 0);
        LocalDateTime testDateTime = LocalDateTime.of(2022, 2, 5, 21, 20);

        long betweenDays = ChronoUnit.DAYS.between(standardDateTime, testDateTime);
        long betweenHours = ChronoUnit.HOURS.between(standardDateTime, testDateTime);

        int standardTurn = 1000;

        // * when
        System.out.println("#####################");
        System.out.println(betweenDays);
        System.out.println(betweenHours);
        System.out.println("#####################");

        if ((betweenDays % 7) == 0) {
            if ((betweenHours - betweenDays * 24) >= 0) {
                standardTurn += (betweenDays / 7) + 1;
            } else {
                standardTurn += (betweenDays / 7);
            }
        } else {
            standardTurn += (betweenDays / 7) + 1;
        }

        System.out.println(standardTurn);
        // * then
    }
}