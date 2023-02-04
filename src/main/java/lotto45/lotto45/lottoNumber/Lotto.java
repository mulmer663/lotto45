package lotto45.lotto45.lottoNumber;

import java.time.LocalDate;

public class Lotto {

    private static long SEQUENCE = 1000001L;
    private long lottoID;
    private int lottoNumber1;
    private int lottoNumber2;
    private int lottoNumber3;
    private int lottoNumber4;
    private int lottoNumber5;
    private int lottoNumber6;
    private LocalDate localDate;

    public Lotto(int lottoNumber1, int lottoNumber2, int lottoNumber3, int lottoNumber4, int lottoNumber5, int lottoNumber6) {
        lottoID = ++SEQUENCE;
        this.lottoNumber1 = lottoNumber1;
        this.lottoNumber2 = lottoNumber2;
        this.lottoNumber3 = lottoNumber3;
        this.lottoNumber4 = lottoNumber4;
        this.lottoNumber5 = lottoNumber5;
        this.lottoNumber6 = lottoNumber6;
        localDate = LocalDate.now();
    }

    public int getLottoNumber1() {
        return lottoNumber1;
    }

    public int getLottoNumber2() {
        return lottoNumber2;
    }

    public int getLottoNumber3() {
        return lottoNumber3;
    }

    public int getLottoNumber4() {
        return lottoNumber4;
    }

    public int getLottoNumber5() {
        return lottoNumber5;
    }

    public int getLottoNumber6() {
        return lottoNumber6;
    }
}
