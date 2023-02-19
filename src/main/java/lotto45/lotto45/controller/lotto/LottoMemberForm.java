package lotto45.lotto45.controller.lotto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lotto45.lotto45.domain.lotto.Lotto;

import java.util.List;

@Data
@AllArgsConstructor
public class LottoMemberForm {

    private Lotto lotto;
    private List<Lotto> last8Numbers;
}
