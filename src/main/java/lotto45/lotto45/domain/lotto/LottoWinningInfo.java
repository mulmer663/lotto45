package lotto45.lotto45.domain.lotto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@ToString
public class LottoWinningInfo {

    @Id
    private int drwNo;
    private String returnValue;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate drwNoDate;
    private int drwtNo1;
    private int drwtNo2;
    private int drwtNo3;
    private int drwtNo4;
    private int drwtNo5;
    private int drwtNo6;
    private int bnusNo;
    private Long totSellamnt;
    private Long firstAccumamnt;
    private int firstPrzwnerCo;
    private Long firstWinamnt;
    @Transient
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
