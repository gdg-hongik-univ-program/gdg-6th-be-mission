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

# 쇼핑몰 API - 3주차

DTO와 Transaction 개념을 적용하여 기존 쇼핑몰 API를 개선하였다.  
Request / Response DTO를 분리하고, Service 계층에 Transaction을 적용하여 보다 안정적인 구조로 구현하였다.

---

## 주요 변경 사항

### DTO 적용
- Request DTO와 Response DTO 분리
- API 요청 및 응답 데이터를 DTO 기반으로 처리

### Transaction 적용
- @Transactional 적용
- 상품 구매 및 재고 수정 시 데이터 일관성 유지

### Response 구조 개선
- Entity를 직접 반환하지 않고 Response DTO 반환
- 필요한 데이터만 클라이언트에 전달하도록 수정

### 계층 구조 유지
- Controller : 요청 처리
- Service : 비즈니스 로직 및 Transaction 처리
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

## DTO 구성

### Request DTO
- ProductCreateRequest
- ProductBuyRequest
- ProductStockUpdateRequest

### Response DTO
- ProductResponse

---

## 배운 점
- DTO를 사용하는 이유
- Entity와 DTO의 역할 차이
- @Transactional의 동작 방식
- 계층 분리 구조의 중요성

# 쇼핑몰 API - 4주차

기존 쇼핑몰 API 코드를 리팩토링하고 Validation 및 예외 처리 기능을 적용하였다.  
잘못된 요청 값과 예외 상황에 대해 일관된 응답을 반환하도록 개선하였다.

---

## 주요 학습 내용

- SOLID 원칙과 리팩토링
- Validation을 활용한 요청 값 검증
- 전역 예외 처리(Exception Handling)

---

## 주요 변경 사항

### Validation 적용
- DTO에 Validation 어노테이션 적용
- @Valid를 활용한 요청 값 검증

```java
@NotBlank
@Positive
@NotNull
@PositiveOrZero
```

## 배운 점

- Validation과 전역 예외 처리 방식
- Custom Exception 활용 방법
- 리팩토링과 코드 구조 개선의 중요성
