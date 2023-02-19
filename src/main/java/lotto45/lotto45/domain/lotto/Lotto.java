package lotto45.lotto45.domain.lotto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.member.Member;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Entity
@Getter
@ToString
@Slf4j
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
    @ToString.Exclude
    private Member member;

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

    // * 생성 메서드
    public static Lotto createLotto() {
        Lotto lotto = new Lotto();
        NavigableSet<Integer> lottoTreeSet = new TreeSet<>();
        Random random = new Random();

        while (lottoTreeSet.size() < 6) {
            int randomNum = random.nextInt(45) + 1;
            lottoTreeSet.add(randomNum);
        }

        lotto.lottoNumber.addAll(lottoTreeSet);

        for (int i = 0; i < lotto.lottoNumber.size(); i++) {
            switch (i) {
                case 0 -> lotto.num1 = lotto.lottoNumber.get(i);
                case 1 -> lotto.num2 = lotto.lottoNumber.get(i);
                case 2 -> lotto.num3 = lotto.lottoNumber.get(i);
                case 3 -> lotto.num4 = lotto.lottoNumber.get(i);
                case 4 -> lotto.num5 = lotto.lottoNumber.get(i);
                case 5 -> lotto.num6 = lotto.lottoNumber.get(i);
                default -> {
                }
            }
        }

        lotto.lottoColorMap = new HashMap<>();
        lotto.dateTime = LocalDateTime.now();

        if (lotto.lottoNumber.size() != 6) {
            throw new RuntimeException("로또 숫자는 6개 입니다.");
        }

        for (int lottoNum : lotto.lottoNumber) {

            if (lottoNum <= 10) {
                lotto.lottoColorMap.put(lottoNum, LottoColor.warning);
            } else if (lottoNum <= 20) {
                lotto.lottoColorMap.put(lottoNum, LottoColor.primary);
            } else if (lottoNum <= 30) {
                lotto.lottoColorMap.put(lottoNum, LottoColor.danger);
            } else if (lottoNum <= 40) {
                lotto.lottoColorMap.put(lottoNum, LottoColor.secondary);
            } else {
                lotto.lottoColorMap.put(lottoNum, LottoColor.success);
            }

        }

        lotto.rounds = lotto.findRounds();

//        log.info("lottoNumber = {}", lotto.lottoNumber);

        return lotto;
    }

    // * 다시 lottoColorMap LottoNumber 리스트에 값을 채워넣는 메소드
    public static void putValuesMapAndList(Lotto lotto) {
        int num1 = lotto.getNum1();
        int num2 = lotto.getNum2();
        int num3 = lotto.getNum3();
        int num4 = lotto.getNum4();
        int num5 = lotto.getNum5();
        int num6 = lotto.getNum6();

        lotto.getLottoNumber().add(num1);
        lotto.getLottoNumber().add(num2);
        lotto.getLottoNumber().add(num3);
        lotto.getLottoNumber().add(num4);
        lotto.getLottoNumber().add(num5);
        lotto.getLottoNumber().add(num6);

        lotto.lottoColorMap = new HashMap<>();
        for (int lottoNum : lotto.lottoNumber) {

            if (lottoNum <= 10) {
                lotto.lottoColorMap.put(lottoNum, LottoColor.warning);
            } else if (lottoNum <= 20) {
                lotto.lottoColorMap.put(lottoNum, LottoColor.primary);
            } else if (lottoNum <= 30) {
                lotto.lottoColorMap.put(lottoNum, LottoColor.danger);
            } else if (lottoNum <= 40) {
                lotto.lottoColorMap.put(lottoNum, LottoColor.secondary);
            } else {
                lotto.lottoColorMap.put(lottoNum, LottoColor.success);
            }

        }
    }
}