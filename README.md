## 개발환경
- mac os(m1)

## 스펙
* Jdk 21
* Springboot 3.3.1
* Spring Data JPA, Querydsl
* H2
* Redis

## 구현 범위에 대한 설명
### 디렉토리 구조
프로젝트는 멀티모듈을 사용하였고 api, bo, core로 구성되어 있습니다.
 - api
   - 유저와 연관엔 api입니다.
   - 현재는 cody에 관련된 api를 포함하고 있습니다.
 - bo
   - back office api입니다.
   - 브랜드, 상품 추가, 업데이트, 삭제 api를 포함하고 있습니다.
 - core
   - entity, repository, config, constant등 공통된 패키지들을 포함하고 있습니다.

### 구현 범위
* 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
  - 카테고리 별로 최저값을 가져와 각 카테고리에서 하나씩 최저값을 비교해 리턴하도룩 하였습니다.
  - 결과 값은 Redis에 caching 되도록 하였고 이는 1일간 유지되거나, 상품 추가 등록 수정 시 삭제되도록 하였습니다.
* 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
  - 먼저 모든 카테고리 상품을 가지고 있는 브랜드를 검색 후 각 브랜드당 최저가를 비교하여 결과를 리턴하였습니다.
  - 결과 값은 Redis에 caching 되도록 하였고 이는 1일간 유지되거나, 상품 추가 등록 수정 시 삭제되도록 하였습니다.
* 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
  - 카테고리 이름에 해당하는 최저, 최고가격 브랜드와 상품을 조회해 리턴하였습니다.
  - 결과 값은 Redis에 caching 되도록 하였고 이는 1일간 유지되거나, 상품 추가 등록 수정 시 삭제되도록 하였습니다.
* 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
  - 브랜드는 브랜드 이름이 겹치지않은 한 추가될 수 있습니다.
  - 상품의 추가, 업데이트, 삭제가 발생 시 위에서 조회하여 caching 된 데이터를 삭제하도록 하였습니다.
* Unit test 및 Integration test 작성
  - unit test와 integration test를 추가하였습니다.
  - unit test는 mock 객체와 stub를 이용하여 실제 로직의 동작이 의도된 대로 동작하는지 확인하였습니다.
  - integration test는 실제 db등 실제 i/o를 통해 결과등이 정상적으로 동작하는지 확인하였습니다.

## 코드 빌드, 테스트, 실행 방법 
* 필수 사항
    * docker 설치
    * docker compose 설치
* build
```shell
$ ./gradlew api:build

$ ./gradlew bo:build
```
* 실행 방법
```shell

  $ docker-compose up -d
  
```
  
