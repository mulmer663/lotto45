package lotto45.lotto45.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LottoWinningInfo {

    private Long totSellamnt;
    private String returnValue;
    private LocalDate drwNoDate;
    private Long firstWinamnt;
    private int drwtNo6;
    private int drwtNo4;
    private int firstPrzwnerCo;
    private int drwtNo5;
    private int bnusNo;
    private Long firstAccumamnt;
    private int drwNo;
    private int drwtNo2;
    private int drwtNo3;
    private int drwtNo1;
    private List<LottoColor> colorList;

    public void makeColorList() {

        List<Integer> drwtNoList = new ArrayList<>();
        drwtNoList.add(drwtNo1);
        drwtNoList.add(drwtNo2);
        drwtNoList.add(drwtNo3);
        drwtNoList.add(drwtNo4);
        drwtNoList.add(drwtNo5);
        drwtNoList.add(drwtNo6);
        drwtNoList.add(bnusNo);

        colorList = new ArrayList<>();

        for (int lottoNum : drwtNoList) {

            if (lottoNum <= 10) {
                colorList.add(LottoColor.warning);
            } else if (lottoNum <= 20) {
                colorList.add(LottoColor.primary);
            } else if (lottoNum <= 30) {
                colorList.add(LottoColor.danger);
            } else if (lottoNum <= 40) {
                colorList.add(LottoColor.secondary);
            } else {
                colorList.add(LottoColor.success);
            }
        }
    }
}
