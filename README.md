# :sparkles: lotto45
> 회원제 로또 추천 사이트 </br>
> h2.bat을 실행 시킨 후 Lotto45Application.java 실행  

## 1. 제작기간 & 참여인원
- 기간 : 2023년 2월 5일 ~ 2023년 2월 17일
- 참여인원 : 2명

## 2. 기술 스택
#### *Back-end*
  - Java 17
  - Spring Boot 3.0.2
  - Gradle
  - Spring Data JPA
  - H2
  - Lombok
#### *Front-end*
  - Thymeleaf
  - Bootstrap
  
## 3. 핵심 기능
> 이 서비스는 비회원, 회원, 관리자 세션별로 로또에 관련한 서비스를 제공합니다.</br>
> 비회원은 단순 번호 추천만 받을 수 있으며</br>
> 회원은 로또 번호 추천, 당첨 정보 조회, 북마크 번호 통계 조회 서비스를 제공 받습니다.</br>
> 관리자는 회원 관리와 당첨 정보 DB를 관리할 수 있습니다.</br>

<details>
<summary><b>:sparkles:핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 3.1 전체 흐름
![](https://github.com/geomusteel/lotto45/blob/main/image/%ED%9D%90%EB%A6%84%EB%8F%84.png)
  
  
### 3.2 비회원, 회원, 관리자의 다중 세션의 구현
- 3개의 인터셉터를 통해 requset 요청을 Interceptor 합니다. :pushpin: [**코드 링크**](https://github.com/geomusteel/lotto45/blob/main/src/main/java/lotto45/lotto45/web/interceptor/LoginMemberInterceptor.java)
  - 관리자는 이중 비밀번호 로그인으로 구현하였기 때문에 두 개의 세션 및 인터셉터를 필요로 합니다.
- Interceptor 순서는 회원 세션 -> 관리자 1차 세션 -> 관리자 2차 세션 순으로 진행됩니다.
![](https://github.com/geomusteel/lotto45/blob/main/image/WebConfig.png)
- 세션은 HomeController와 LoginController 에서 체크한 후 해당 세션에 알맞은 페이지로 연결해 줍니다.
![](https://github.com/geomusteel/lotto45/blob/main/image/HomeController.png)


### 3.3 백엔드에서의 API 호출 및 Mapping
- RestTemplate와 ObjectMapper을 활용해 백엔드 단에서 API를 호출하고 도메인에 매핑하는 기능입니다.
- 회차별 당첨정보를 가져오며 JSON 형식의 String 자료를 ObjectMapper를 이용해 도메인에 매핑합니다.
![](https://github.com/geomusteel/lotto45/blob/main/image/SearchAPIController.png)

### 3.4 회원가입 입력 검증 기능
- Controller 단에서 두 가지 글로벌 에러를 검증합니다.
![](https://github.com/geomusteel/lotto45/blob/main/image/MemberController.png)
- MemberDTO에 validation 어노테이션을 넣어 회원가입 입력 검증을 수행합니다.
![](https://github.com/geomusteel/lotto45/blob/main/image/MemberDTO.png)
  
### 3.5 순수 백엔드로 구현한 북마크
- Thymeleaf를 활용해 List<Lotto> 를 순회하면서 인덱스마다 도메인 필드의 bookmark를 매핑합니다. 
![](https://github.com/geomusteel/lotto45/blob/main/image/lottoNumberPlus.png)
- Controller에서 Post로 받아온 LottoMemberForm 객체에서 북마크 정보를 추출해 따로 저장하고 있는 큐에 반영합니다.
![](https://github.com/geomusteel/lotto45/blob/main/image/LottoNumberController.png)
</details>
</br>

## 4. 트러블슈팅 경험
### 4.1 도메인 생성자에 로직을 넣어서 생긴 문제

<details>
<summary><b>:sparkles:기존 코드</b></summary>
<div markdown="1">
</br>

:pushpin: [**기존 코드 링크**](https://github.com/geomusteel/lotto45/blob/694871aa61c8ca3a4f6c8e7e01c3c1d9dbd9e872/src/main/java/lotto45/lotto45/domain/lotto/Lotto.java)
</br>
- Lotto 도메인 기본 생성자에 번호를 랜덤으로 넣는 로직을 넣어 Lotto 인스턴스를 생성할 때 마다 랜덤한 번호를 가지게 끔 구현하였다.
- 이 생성자 로직은 후에 치명적인 예외를 발생시키는데 View에서 Controller로 Lotto 객체를 전달 할 때 새로운 Lotto 객체를 생성해 전달해 준다.
- 문제는 새로운 객체를 만들 때 마다 기존의 번호를 반영하는게 아닌 생성자 로직으로 인한 새로운 번호를 Controller에 전달한다는 점이고
- 결국에는 페이지에서 보여지는 로또의 번호와 DB에 저장하는 로또의 번호가 달라지는 치명적인 예외를 발생시킨다.

</div>
</details>

<details>
<summary><b>:sparkles:개선된 코드</b></summary>
<div markdown="1">
</br>

:pushpin: [**개선 코드 링크**](https://github.com/geomusteel/lotto45/blob/e8e0fdcb13/src/main/java/lotto45/lotto45/domain/lotto/Lotto.java)
</br>
- 생성자에 있던 로직으로 인한 문제이므로 생성자에 있던 로직을 static createLotto() 생성 메서드로 옮겨준다. 
- 백엔드에서는 new로 선언했던 모든 도메인 인스턴스들을 생성 메서드를 통해 생성하도록 변경해주었다.
- 이제는 View에서 Controller로 새로운 Lotto 객체를 만들어서 서버에 보내줄 때에 제대로 기존의 번호를 반영하게 된다.

</div>
</details>

### 4.2 스프링 빈 싱글톤 때문에 접속하는 모든 클라이언트가 같은 로또 추천 화면을 공유하는 문제

<details>
<summary><b>:sparkles:기존 코드</b></summary>
<div markdown="1">
</br>

:pushpin: [**기존 코드 링크**](https://github.com/geomusteel/lotto45/blob/c2a73f99ad/src/main/java/lotto45/lotto45/service/lotto/LottoNumberServiceImp.java)
</br>
- 스프링 컨테이너에 등록된 빈은 싱글톤 객체로서 모든 클라이언트가 같은 빈을 공유하게 된다.
- 그렇기에 로또 번호를 추천하는 페이지에서 서로 다른 클라이언트들이 보는 추천 번호가 전부 같은 문제가 생기게 된다.
- 또한 A클라이언트가 번호 생성을 누르고 B클라이언트가 번호 생성을 눌렀을 때 아까 A클라이언트가 만든 번호가 반영되어 B클라이언트에는 번호가 2개가 생성된다. 
</div>
</details>

<details>
<summary><b>:sparkles:개선된 코드</b></summary>
<div markdown="1">
</br>

~~~java

@Slf4j
@Service
@RequiredArgsConstructor
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LottoNumberServiceImp implements ILottoNumberService {

~~~
- 각 클라이언트 세션별로 서로 다른 번호 추천 화면을 보여주어야 한다.
- 웹 스코프의 session 스코프를 활용해 주어야 하고 추가로 실제 요청시 까지 지연처리하는 프록시 객체를 활용해준다.
- 회원 번호 추천 기능 중 Service 계층에 @Scope 써서 위의 기능들을 추가해준다.
</div>
</details>

### 4.3 JPA 영속화와 @Transient으로 생기는 문제

<details>
<summary><b>:sparkles:기존 코드</b></summary>
<div markdown="1">
</br>

:pushpin: [**기존 코드 링크**](https://github.com/geomusteel/lotto45/blob/694871aa61/src/main/java/lotto45/lotto45/domain/lotto/Lotto.java)
</br>
- 도메인 Lotto 필드 전부를 Column으로 DB에 저장하지 않고 일부만 저장하기 때문에 DB에 저장하지 않는 필드에는 @Transient를 붙여준다.
- 문제는 저장할 때가 아니고 DB의 Table에서 Lotto 번호를 꺼내 Lotto 도메인에 매핑할 때 생긴다.
- Lotto 도메인에 매핑할 때 @Transient가 붙여져 있는 필드는 전부 null 값을 가지게 되고 이 때문에 해당 필드를 참조하는 메소드들에서 전부 NullPointerException이 발생하게 된다.  
</div>
</details>

<details>
<summary><b>:sparkles:개선된 코드</b></summary>
<div markdown="1">

~~~java

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

~~~
- 따라서 Lotto 도메인에서 @Transient를 붙여준 필드에 다시 값을 넣는 static 메소드를 추가해준 뒤
- Service에서 해당 메소드를 호출해 모든 필드에 제대로 값이 들어가도록 한다.
</div>
</details>

## 개선할 점과 느낀 점
1. [**개선할 점**]이 때는 코드를 공부한지 3개월 차여서 자바와 스프링만 알고 활용할 수 있었던 상황이였다. <br>
그렇기에 JavaScript와 ajax를 알지 못해 해당 기능을 프로젝트에 추가해 줄 수 없었다. <br>
북마크 기능을 단순 스프링으로 구현하니 코드도 복잡해지고 불필요한 계산을 하게 되는 문제가 생긴다. <br>
따라서 이제는 활용할 수 있는 JS와 ajax를 이용해 북마크 기능을 구현하도록 개선해야한다.

2. [**개선할 점**]Http는 무상태라는 특성을 가지고 있고 스프링 빈들도 무상태 특성을 가지고 있어야한다. <br>
하지만 MemberLottoServiceImp.java와 MemoryNonMemberLottoRepositoryImp.java에 <br>
화면에 보여지는 로또 추천 번호를 필드인 Queue에 저장하게 끔 구현되어 있다. <br>
즉 무상태를 아닌것이다. 이 뜻은 이 프로젝트가 많은 사람이 이용하는 프로젝트면 순식간에 서버의 메모리가 가득차 셧다운 되는 문제를 가지게 된다. <br>
이를 해결하기 위해선 쿠키를 활용해 클라이언트가 화면에 보이는 추천 번호를 가지고 있게끔 개선해야 된다.

3. [**느낀점**] 이렇게 조악하고 많은 기능도 없는 프로젝트에 이토록 많은 클래스가 필요했다. <br>
또한 코드를 쓰고 테스트 하면서 마주하는 수많은 예외를 해결해야 했다. <br>
실제 복잡하고 방대한 프로젝트를 가진 실무에서는 무엇보다 협업이 중요하다고 느꼈고 개인의 생산성 보다는 남들과 맞출 줄 아는 능력이 더 중요하다고 느꼈다. <br>
프로그래밍을 공부한 시간이 약 3개월 차였고 짧은 시간안에 공부를 병행하면서 빠르게 만든다고 JUnit을 별로 활용하지 못한점이 아쉽게 느껴졌다. <br>
하지만 내가 직접 만든 프로젝트가 의도대로 잘 작동되는 것을 보고 정말로 많은 보람을 느꼈다.
