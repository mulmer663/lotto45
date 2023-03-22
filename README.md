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
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 3.1 전체 흐름
![](https://github.com/geomusteel/lotto45/blob/main/image/%ED%9D%90%EB%A6%84%EB%8F%84.png)
  
  
### 3.2 다중 세션의 구현
- 3개의 인터셉터를 통해 requset 요청을 Interceptor 합니다. :pushpin: [코드 링크](https://github.com/geomusteel/lotto45/blob/main/src/main/java/lotto45/lotto45/web/interceptor/LoginMemberInterceptor.java)
  - 관리자는 이중 비밀번호 로그인으로 구현하였기 때문에 두 개의 세션 및 인터셉터를 필요로 합니다.
- Interceptor 순서는 회원 세션 -> 관리자 1차 세션 -> 관리자 2차 세션 순으로 진행됩니다.
![](https://github.com/geomusteel/lotto45/blob/main/image/WebConfig.png)
- 세션은 HomeController와 LoginController 에서 체크한 후 해당 세션에 알맞은 페이지로 연결해 줍니다.
![](https://github.com/geomusteel/lotto45/blob/main/image/HomeController.png)


### 3.3 백엔드에서의 API 호출 및 Mapping
- RestTemplate와 ObjectMapper을 활용해 백엔드 단에서 API를 호출하고 도메인에 매핑하는 기능입니다.
- 코드 부분 (SearchAPIController)

### 3.4 회원가입 입력 검증 기능
- Controller 단에서 두 가지 글로벌 에러를 검증합니다.
- 코드 부분 (MemberController)
- MemberDTO에 validation 어노테이션을 넣어 회원가입 입력 검증을 수행합니다.
- 코드 부분 (MemberDTO)
  
### 3.5 순수 백엔드로 구현한 북마크
- Thymeleaf를 활용해 List<Lotto> 를 순회하면서 인덱스마다 도메인 필드의 bookmark를 매핑합니다. 
- 코드 부분 (lottoNumberPlus)
- Controller에서 Post로 받아온 LottoMemberForm 객체에서 북마크 정보를 추출해 따로 저장하고 있는 큐에 반영합니다.
- 코드 부분 (LottoNumberController)
</details>
</br>

## 4. 트러블슈팅 경험
### 4.1 도메인 생성자에 로직을 넣어서 생긴 문제

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">
- 내용
</div>
</details>

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">
- 내용
</div>
</details>

### 4.2 스프링 빈 싱글톤 때문에 접속하는 모든 클라이언트가 같은 로또 추천 화면을 공유하는 문제

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">
- 내용
</div>
</details>

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">
- 내용
</div>
</details>

### 4.3 JPA 영속화와 @Transient으로 생기는 문제

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">
- 내용
</div>
</details>

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">
- 내용
</div>
</details>

## 개선할 점/ 느낀 점
