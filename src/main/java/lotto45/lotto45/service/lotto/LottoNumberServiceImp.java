package lotto45.lotto45.service.lotto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.lotto.Lotto;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.repository.lotto.IMemberLottoRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LottoNumberServiceImp implements ILottoNumberService {

    private final IMemberLottoRepository memberLottoRepository;
    private final Deque<Lotto> lottoQueue = new ArrayDeque<>();

    @Override
    public Lotto create() {
        Lotto lotto = new Lotto();

        if (lottoQueue.size() < 8) {
            lottoQueue.add(lotto);
        } else if (lottoQueue.size() == 8) {
            lottoQueue.poll();
            lottoQueue.add(lotto);
        }

        return lotto;
    }

    @Override
    public List<Lotto> lastLottoNumber8() {
        List<Lotto> lottoList = new ArrayList<>();

        for (Lotto lotto : lottoQueue) {
            lottoList.add(0, lotto);
        }

        return lottoList;
    }

    @Override
    @Transactional
    public void saveBookMarkedLotto(List<Lotto> lottoList, Member member) {

        for (Lotto lotto : lottoList) {
            lotto.setMember(member);
        }
        Long memberId = member.getId();

        List<Lotto> savedLottoList = this.memberLottoRepository.findByMemberId(memberId);

        // 북마크 된 로또 인지 체크하고 중복도 제거
        // # 추가로 Id가 있는 lotto는  이미 저장한 리스트 이므로 필터로 걸러내야한다! 아니면 JPA에서 DETACH 상태가 되므로 예외를 던진다!
        // # merge로 업데이트 하던가 아님 그냥 id잇는걸 없애던가 둘 중 하나!
        List<Lotto> bookMarkedLottoList = lottoList.stream()
                .filter(Lotto::isBookmark)
                .filter(l -> l.getId() == null)
                .filter(l -> !savedLottoList.contains(l))
                .toList();

        this.memberLottoRepository.save(bookMarkedLottoList, memberId);
    }

    @Override
    public List<Lotto> findAll(long memberId) {
        return this.memberLottoRepository.findByMemberId(memberId);
    }

    @Override
    @Transactional
    public void removeUnBookMarkedLotto(List<Lotto> lottoList, long memberId) {

        List<Lotto> savedLottoList = this.memberLottoRepository.findByMemberId(memberId);

        // 북마크가 해제된 로또 인지 체크하고 저장된 로또인지도 체크
        List<Lotto> toEraseLottoList = lottoList.stream()
                .filter(l -> !l.isBookmark())
                .filter(savedLottoList::contains)
                .toList();

        this.memberLottoRepository.delete(toEraseLottoList);
    }
}
