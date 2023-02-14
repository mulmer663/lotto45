package lotto45.lotto45.service.lotto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 필요한 당첨 통계 정보로는 무엇이 있을까??
 * <p>1. 몇 번째 회차인지 저장하는 필드</p>
 * <p>2. 회차의 어떤 번호와 일치하는지 저장하는 필드</p>
 * <p>3. 몇 등인지 저장하는 필드</p>
 * <p>4. 만약 2등 당첨이면 보너스 번호가 무엇인지</p>
 * <p>4. 만약 1등이면 당첨 금액을 저장하는 필드</p>
 */
@Data
public class WinSttInfoDTO {

    private int rounds;
    private List<Integer> lottoNums = new ArrayList<>();
    private int rank;
    private int bonusNum;
    private long winAmount;

}
