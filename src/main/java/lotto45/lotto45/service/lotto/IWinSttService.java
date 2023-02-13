package lotto45.lotto45.service.lotto;

import lotto45.lotto45.domain.lotto.WinSttInfo;

import java.util.List;

public interface IWinSttService {

    /**
     * 당첨 정보 DB에 있는 모든 정보를 가져와 입력한 6개의 숫자가 지난 회차에서 당첨될 이력이 있는지 계산하는 메소드
     * 그래서 이걸 하면 WinSttInfo 클래스를 가진 list를 가져오고 이를 컨트롤러 및 뷰에 전달
     */
    List<WinSttInfo> calculateSttInfo();

}
