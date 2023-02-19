package lotto45.lotto45.domain.lotto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lotto45.lotto45.domain.member.Member;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Entity
@Getter
public class Lotto {

    @Setter
    @Id
    @GeneratedValue
    @Column(name = "lotto_id")
    private Long id;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;
    private int rounds;
    @Transient
    private Map<Integer, LottoColor> lottoColorMap;
    @Transient
    private List<Integer> lottoNumber = new ArrayList<>();
    @Transient
    @Setter
    private boolean bookmark; // 북마크 여부
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Lotto() {

        NavigableSet<Integer> lottoTreeSet = new TreeSet<>();
        Random random = new Random();

        while (lottoTreeSet.size() < 6) {
            int randomNum = random.nextInt(45) + 1;
            lottoTreeSet.add(randomNum);
        }

        this.lottoNumber.addAll(lottoTreeSet);

        for (int i = 0; i < this.lottoNumber.size(); i++) {
            switch (i) {
                case 0 -> num1 = lottoNumber.get(i);
                case 1 -> num2 = lottoNumber.get(i);
                case 2 -> num3 = lottoNumber.get(i);
                case 3 -> num4 = lottoNumber.get(i);
                case 4 -> num5 = lottoNumber.get(i);
                case 5 -> num6 = lottoNumber.get(i);
                default -> {
                }
            }
        }

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

    // + 연관관계 메서드
    public void setMember(Member member) {
        this.member = member;
//        member.getLottos().add(this);
    }

}