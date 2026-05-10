# 6th Backend Mission Course

2026년 1학기 GDG Hongik Univ. 프로젝트 트랙의 백엔드 미션코스 입니다.  
프로젝트 진행을 위해 필요한 지식과 경험을 쌓습니다.

# 쇼핑몰 API - 1주차

간단한 쇼핑몰 API를 구현했습니다.  
상품 등록, 조회, 재고 관리, 구매 기능을 REST API 형태로 구현했습니다.

---

## Controller 구조

```plaintext
ProductAdminController
├─ 상품 등록
├─ 재고 추가
└─ 상품 삭제

ProductUserController
├─ 상품 조회
├─ 전체 상품 조회
└─ 상품 구매
```

## API 목록

## Admin

- POST /admin/product
- PATCH /admin/product/{id}/stock
- DELETE /admin/products

## User

- GET /products
- GET /products/{name}
- POST /buy

## 구현 방식

- ProductStore 리스트 기반 저장
- sequence 값으로 상품 id 관리
- 구매 시 재고 확인 후 차감
- Controller 기준으로 Admin / User 분리

## 배운 점

- HTTP Method의 역할 차이
- PathVariable과 Query Parameter 사용 방식
- REST API 구조에 맞게 Controller와 URL 분리

# 쇼핑몰 API - 2주차
JPA와 H2 Database를 활용하여 기존 쇼핑몰 API를 개선하였다.  
Controller / Service / Repository 계층 구조를 분리하고 Entity 기반으로 데이터를 관리하도록 구현하였다.

---

## 사용 기술
- Spring Boot
- Spring Data JPA
- H2 Database
- Lombok
- Gradle

---

## 주요 변경 사항

### Entity 적용
- Product 클래스를 Entity로 변경
- @Entity, @Id, @GeneratedValue 적용

### Repository 적용
- JpaRepository 기반 Repository 구현
- CRUD 기능 사용

### 계층 분리
- Controller : 요청 처리
- Service : 비즈니스 로직 처리
- Repository : DB 접근 처리

---

## 구현 기능

### Admin API
- 상품 등록
- 재고 추가
- 여러 상품 삭제

### User API
- 상품 조회
- 전체 상품 조회
- 상품 구매

---

## API 테스트
test.http 파일을 사용하여 API를 테스트하였다.