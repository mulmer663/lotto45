package lotto45.lotto45.domain;

import java.time.LocalDateTime;

public class Lotto {

    private static long SEQUENCE = 1000001L;
    private long lottoID;
    private int lottoNumber1;
    LottoColor lottoNumber1Color;
    private int lottoNumber2;
    LottoColor lottoNumber2Color;
    private int lottoNumber3;
    LottoColor lottoNumber3Color;
    private int lottoNumber4;
    LottoColor lottoNumber4Color;
    private int lottoNumber5;
    LottoColor lottoNumber5Color;
    private int lottoNumber6;
    LottoColor lottoNumber6Color;
    private LocalDateTime localDateTime;

    public Lotto(int lottoNumber1, int lottoNumber2, int lottoNumber3, int lottoNumber4, int lottoNumber5, int lottoNumber6) {
        lottoID = ++SEQUENCE;

        this.lottoNumber1 = lottoNumber1;
        if (lottoNumber1 <= 10) {
            lottoNumber1Color = LottoColor.warning;
        } else if (lottoNumber1 <= 20) {
            lottoNumber1Color = LottoColor.primary;
        } else if (lottoNumber1 <= 30) {
            lottoNumber1Color = LottoColor.danger;
        }else if (lottoNumber1 <= 40) {
            lottoNumber1Color = LottoColor.secondary;
        } else {
            lottoNumber1Color = LottoColor.success;
        }

        this.lottoNumber2 = lottoNumber2;
        if (lottoNumber2 <= 10) {
            lottoNumber2Color = LottoColor.warning;
        } else if (lottoNumber2 <= 20) {
            lottoNumber2Color = LottoColor.primary;
        } else if (lottoNumber2 <= 30) {
            lottoNumber2Color = LottoColor.danger;
        }else if (lottoNumber2 <= 40) {
            lottoNumber2Color = LottoColor.secondary;
        } else {
            lottoNumber2Color = LottoColor.success;
        }

        this.lottoNumber3 = lottoNumber3;
        if (lottoNumber3 <= 10) {
            lottoNumber3Color = LottoColor.warning;
        } else if (lottoNumber3 <= 20) {
            lottoNumber3Color = LottoColor.primary;
        } else if (lottoNumber3 <= 30) {
            lottoNumber3Color = LottoColor.danger;
        }else if (lottoNumber3 <= 40) {
            lottoNumber3Color = LottoColor.secondary;
        } else {
            lottoNumber3Color = LottoColor.success;
        }

        this.lottoNumber4 = lottoNumber4;
        if (lottoNumber4 <= 10) {
            lottoNumber4Color = LottoColor.warning;
        } else if (lottoNumber4 <= 20) {
            lottoNumber4Color = LottoColor.primary;
        } else if (lottoNumber4 <= 30) {
            lottoNumber4Color = LottoColor.danger;
        }else if (lottoNumber4 <= 40) {
            lottoNumber4Color = LottoColor.secondary;
        } else {
            lottoNumber4Color = LottoColor.success;
        }

        this.lottoNumber5 = lottoNumber5;
        if (lottoNumber5 <= 10) {
            lottoNumber5Color = LottoColor.warning;
        } else if (lottoNumber5 <= 20) {
            lottoNumber5Color = LottoColor.primary;
        } else if (lottoNumber5 <= 30) {
            lottoNumber5Color = LottoColor.danger;
        }else if (lottoNumber5 <= 40) {
            lottoNumber5Color = LottoColor.secondary;
        } else {
            lottoNumber5Color = LottoColor.success;
        }

        this.lottoNumber6 = lottoNumber6;
        if (lottoNumber6 <= 10) {
            lottoNumber6Color = LottoColor.warning;
        } else if (lottoNumber6 <= 20) {
            lottoNumber6Color = LottoColor.primary;
        } else if (lottoNumber6 <= 30) {
            lottoNumber6Color = LottoColor.danger;
        }else if (lottoNumber6 <= 40) {
            lottoNumber6Color = LottoColor.secondary;
        } else {
            lottoNumber6Color = LottoColor.success;
        }

        localDateTime = LocalDateTime.now();

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

    public LottoColor getLottoNumber1Color() {
        return lottoNumber1Color;
    }

    public LottoColor getLottoNumber2Color() {
        return lottoNumber2Color;
    }

    public LottoColor getLottoNumber3Color() {
        return lottoNumber3Color;
    }

    public LottoColor getLottoNumber4Color() {
        return lottoNumber4Color;
    }

    public LottoColor getLottoNumber5Color() {
        return lottoNumber5Color;
    }

    public LottoColor getLottoNumber6Color() {
        return lottoNumber6Color;
    }

    public long getLottoID() {
        return lottoID;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
