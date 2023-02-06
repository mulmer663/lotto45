package lotto45.lotto45.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Entity
@Getter
public class Lotto {

    @Id
    @GeneratedValue
    @Setter
    private long id;
    @ElementCollection
    private List<Integer> lottoNumber = new ArrayList<>();
    @ElementCollection
    private Map<Integer, LottoColor> lottoColorMap;
    private LocalDateTime dateTime;
    private int rounds;

    public Lotto() {
        NavigableSet<Integer> lottoTreeSet = new TreeSet<>();

        while (lottoTreeSet.size() < 6) {
            lottoTreeSet.add((int)(Math.random()*45)+1);
        }

        this.lottoNumber.addAll(lottoTreeSet);
        this.lottoColorMap = new HashMap<>();
        this.dateTime = LocalDateTime.now();

        if (this.lottoNumber.size() != 6) {
            throw new RuntimeException("로또 숫자는 6개 입니다.");
        }

        for (int lottoNum : this.lottoNumber) {

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

        this.rounds = this.findRounds();
    }

    private int findRounds() {

        // 기준 회차를 1000회차를 기준으로 잡음
        LocalDateTime standardDateTime = LocalDateTime.of(2022, 1, 29, 22, 0);
        long betweenDays = ChronoUnit.DAYS.between(standardDateTime, this.dateTime);
        long betweenHours = ChronoUnit.HOURS.between(standardDateTime, this.dateTime);
        int standardRounds = 1000;

        if ((betweenDays % 7) == 0) {
            if ((betweenHours - betweenDays * 24) >= 0) {
                standardRounds += (betweenDays / 7) + 1;
            } else {
                standardRounds += (betweenDays / 7);
            }
        } else {
            standardRounds += (betweenDays / 7) + 1;
        }
        return standardRounds;
    }
}