package lotto45.lotto45.lottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Lotto 번호 생성에 사용될
 * 객체
 */
public class LottoNumberArrayList {

    List<Integer> lottoNum45 = new ArrayList<>(45);

    public LottoNumberArrayList(){
        for(int i = 1 ; i<46 ;i++){
            lottoNum45.add(i);
        }
    }

    public void shuffle(){
        Collections.shuffle(lottoNum45);
    }

    public List<Integer> sixLottoNum(){
        List<Integer> lottoNumbers = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            lottoNumbers.add(lottoNum45.get(i));
        }
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }


} // end class
