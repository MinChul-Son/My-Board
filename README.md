# 📝게시판 만들기
## 💻 사용 기술 
### #Back-End
#### Core
* JAVA 8
* Spring MVC
* Spring Boot
* Spring Data JPA
* Spring Security
* Querydsl

#### Build Tool
* Gradle

#### Database
* Mysql

### #Front-End
* Javascript
* Thymeleaf
* BootStrap


## 💻 개발환경
* IntelliJ
* Postman
* Github(Git bash)

-----------------------------------
## 주요 기능 소개
### version 1.0(초기 버전)

* [로그인/로그아웃/회원가입](https://github.com/MinChul-Son/My-Board/issues/1)
* [게시글 작성](https://github.com/MinChul-Son/My-Board/issues/2)
* [게시글 삭제/수정](https://github.com/MinChul-Son/My-Board/issues/4)
  - 자신의 게시글만 삭제/수정 가능
* [특정 게시물 조회, 게시글 목록 조회](https://github.com/MinChul-Son/My-Board/issues/3)
  - 카테고리별 조회
  - 내가 작성한 게시글 조회
* [게시글 검색](https://github.com/MinChul-Son/My-Board/issues/7)
  - 제목으로 검색
  - 작성자로 검색
  - 내용으로 검색 
* [조회수 기능](https://github.com/MinChul-Son/My-Board/issues/5)
* [페이징 기능](https://github.com/MinChul-Son/My-Board/issues/6)





----------------------------------
## 추가하면 좋을 기능 구상 중!(점진적으로 기능 추가 + 업데이트 예정)
* 추가적으로 관리자 페이지를 설정할 예정
  - 관리자는 회원의 게시물을 삭제 가능
  - 관리자는 회원 목록을 관리 가능
    + 추방, 조회

* 메시지, 국제화 기능 적용시킬 예정
* 게시물 검증 기능 추가 예정
  - 게시물 작성할 때 제목은 꼭 있어야하고, 내용은 몆 글자 이상 등등...

* 게시물에 추천을 누를 수 있도록 하여 일정 추천수 도달시 HOT 게시판으로 이동
* 예외처리도 추가해보자(이번에 공부한 `BasicErrorController`를 사용해서) ==> API통신을 추가하면 `@ExceptionHandler`을 사용하면됨.
* 추가 프로젝트로 **API 서버**로 변경시켜보자(나중에..)
  - 패키지 구조를 변경해야할 수도 있음. 아예 별도의 프로젝트로 생성하는 것이 좋을 듯 


----------------------------------
--------------------------------
# 날짜별 To-Do-List
### 6/20
* 프로젝트 생성
* 게시글 작성, DB에 저장 기능 추가

### 6/21 to-do-list
* `게시글 목록 조회` ~~예정~~ **완료**
* `게시글 리스트 UI 적용` ~~예정~~ **완료**
* `게시물 조회` 기능 추가 ~~예정~~ **완료**
* `게시물 삭제` 기능 추가 ~~예정~~ **완료**
* `게시물 수정` 기능 추가 ~~예정~~ **완료**

### 6/22 to-do-list
* `게시물 조회수` 기능 추가 ~~예정~~ **완료**
* `게시물 페이징` 기능 추가 ~~예정~~ **완료**
* `로그인/로그아웃, 회원가입` 기능 추가 예정 **내일 이어서 진행**
  - `Spring Security`를 사용
  - 기본으로 제공하는 login 페이지 사용 
  - 로그인 안한 사용자는 index페이지만 접속 가능하도록 구현 **완료**
  - 각 회원이 어떤 권한을 가지는지에 대한 Role 엔티티를 추가하고 Member와 다대일로 양방향 매핑 **완료**
* `Member` 엔티티 추가, `Post` 엔티티에서 `1:N` 관계로 `단방향 연관관계`로 매핑 ~~예정~~ **완료**
  - **누가 게시물을 작성했는지를 입력하는 것이 아니라 로그인한 회원의 정보를 가지고 값을 자동으로 넣을 것이기 때문에 Member 입장에서는 Post를 알 필요가 없다고 생각하여 단방향 연관관계**로 설정하였다. 추후에 만약 내 생각이 틀렸다고 판단되면 변경할 수 도 있는 부분임


### 6/23 to-do-list
* `Role 엔티티 변경`
  - `Role`을 엔티티로 정의하고 `Member`와 다대일 양방향 매핑을 진행하였었는데, 이를 위해서 Role Repository를 만들고 추가적인 기능추가가 너무 번거롭다고 판단하여 **`Role`을 `Enum`타입**으로 정의하여 사용하는 것으로 변경하였다.
  - 회원 가입시에 전부 다 USER 권한을 부여하는 것으로 설정

* `회원 가입, 로그인` 기능 **완료**
  - 회원 가입 시에 `패스워드를 암호화`하여 DB에 저장하도록 하였다.(Spring Security)
  - `로그인 여부`에 따라 홈 화면에서 나타나는 화면을 달리하도록 설정
* `로그아웃` 기능 추가 ~~예정~~ **완료**
* 게시물 작성 시 **세션 정보를 가지고 작성자를 자동으로 입력**해주고 작성자는 변경할 수 없도록 기능 변경 ~~예정~~ **완료**
* 현재 Member와 Post는 1:N 단방향 연관관계로 Post에서만 Member를 참조하도록 매핑되어 있다. 게시물을 저장할 때 `세션의 정보`(id)를 가지고 `Member 엔티티를 조회`하고 `Post 엔티티의 Member 필드로 설정`해준다.
  - 이것을 바탕으로 추후에 검색기능(동적 쿼리)추가할 것
  - 세션 정보를 가지고 게시물의 회원 정보와 일치한다면? 글을 삭제할 수 있는 기능을 추가해보기(내일 ㄱㄱ)
    + 즉, 내 게시물은 나만 삭제할 수 있는것 


### 6/24 to-do-list
* `게시물을 작성한 사람만 게시물을 지울 수 있는` 기능 추가 ~~예정~~ **완료**
  - `BoardService`에 `isWriter()`메서드 구현
  - 게시물 조회 시에 메서드 호출
    + 인증 정보로 조회한 회원 엔티티와 게시물의 회원 정보로 조회한 엔티티가 같다면 삭제 버튼 띄워줌. 
    + `fetch join`을 사용하여 게시물에서 회원 엔티티 조회
  - JUnit 테스트 코드 작성 **테스트 성공**
* Junit 테스트 코드 작성(회원 저장, 게시물 저장, 게시물에서 회원 엔티티 조회 ==> 1:N 단방향 연관관계) **테스트 성공 (커버리지 87%)** 
* 자신이 작성한 게시물 목록 조회 기능 추가 ~~예정~~ **완료**
  - `@EntityGraph`를 사용하여 `fetch join`을 통해 파라미터로 넘어온 회원 아이디와 일치하는 게시물 조회


### 6/25 to-do-list
* `Post`에 `Enum`타입 카테고리 필드 추가 ~~예정~~ **완료**
* `카테고리 별 게시물 조회` 기능 추가 예정
  - 드롭다운으로 카테고리 선택하려고 했는데 드롭다운 자꾸 동작을 안하네 짜증나... 내일 다시 해보자 -_-
* `게시물 검색 기능` 추가 예정
  - html select를 통해 검색 옵션 선택 가능
    + 작성자로 검색
    + 제목으로 검색
    + 내용으로 검색


### 6/28 to-do-list
* 쿼리파라미터로 넘어온 카테고리 값을 컨트롤러에서 받아서 해당 `카테고리 게시물 조회` 기능 추가 ~~예정~~ **완료**
  - 카테고리별 조회 메서드 구현
  - 서비스 계층에서 이를 받아서 메서드 사용
  - 페이징 처리는 기존과 동일하게
  - **DTO로 반환할 것!**
  - 쿼리파라미터로 `Enum`타입인 `category`를 받는다. `required=false` 옵션을 통해 값이 없으면 `null`이 들어가도록 설정하였다.
    + category가 null이면 전체조회, 값이 있으면 `findByCategory` 메서드를 실행하여 해당 카테고리 게시물 조회 
* 검색 기능도 하나 추가해야함.
  - GetMapping 경로 ==> /board/search
  - 쿼리 파라미터로 searchValue를 받아서 조회

-------------------------------

## 6/28일 로직, 설계 변경
### 현재 게시물을 조회하는 case에는 여러가지가 존재한다.
1. 전체 게시물 조회
2. 카테고리별 조회
3. 내가 작성한 게시물 조회
4. 키워드로 게시물 검색

#### 내가 의도한 것은 이 기능을 모두 섞어서 사용할 수 있도록 만들고 싶었다.
* 예를 들어, 내가 작성한 게시물 중에서 카테고리 별로 조회
* 내가 작성한 게시물 중에서 검색

#### 하지만 컨트롤러 매핑을 각각 따로 나누다보니 이것이 굉장히 복잡해졌다.
* 이를 위해선 각각의 컨트롤러에 모두 동일하게 `@RequestParam`이나 `@ModelAttribute`로 값을 계속 저장해가면서 움직여아함

#### 따라서 조회를 하는 컨트롤러는 하나로 통합하여 사용하는 것으로 설계를 바꾸었다.
* 이렇게 변경하면 같은 컨트롤러에서 파라미터들이 돌기 때문에 model에 값을 담아 뷰에서도 사용이 가능하므로 훨씬 용이해진다.
* 페이징 처리도 편리하게 가능
```html
th:href="@{/board(page=${postList.number}-1, category=${selectedCategory}, myPost=${myPost})}
```
#### 하나의 컨트롤러로 여러 개의 파라미터를 받다보니 위와 같이 조회하는 case가 여러가지 존재한다. 각 case에 해당하는 메서드를 각각 만들고 이를 if-else에 따라 실행시키는 것은 매우 좋지 않은 방법이다. 그럼 어떻게 처리해야할까? Querydsl을 통해 이런 동적쿼리를 손쉽게 만들 수 있다.
* 현재 Querydsl을 공부하고 있기 때문에 얼른 공부하고 구현해보자!
* Querydsl을 사용하면 null에 해당하는 where문 조건을 무시할 수 있음 ==> **정확히 내가 필요한 기능임!!**

#### 초기 설계가 굉장히 중요함을 느낀 계기였다.

----------------------------------

### 6/30 to-do-list
* `Querydsl`을 사용하여 `동적쿼리 기능` **완료!**
  - `BooleanExpression`을 사용하여 파라미터로 넘어온 값(category, mypost, searchDto, username)을 처리하고` where절에서 결과를 필터링`한다.
  - `Querydsl`을 사용하니 굉장히 편리하게 값을 필터링할 수 있는 것 같다.
  - 추가된 클래스
    + `QuerydslConfig` : `JPAQueryFactory`를 스프링 빈으로 등록
    + `BoardRepositoryCustom` : 커스텀 인터페이스
    + `BoardRepositoryImpl` : 커스텀 인터페이스 구현체)
* list.html에서 searchDto의 값들을 페이지를 이동할 때도 계속 가지고와 사용할 수 있도록 하였다.
  - 검색창에 검색을 한 상태에서 다음 페이지 버튼을 누르면 검색창으로 검색한 결과에 대한 다음 페이지로 이동 
* 현재 초기에 구상했던 게시판의 기능은 모두 구현을 했다.

-----------------------------------------------------------------
