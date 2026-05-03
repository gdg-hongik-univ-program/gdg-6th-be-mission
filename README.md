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

API 목록

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

- HTTP Method 역할 차이
- PathVariable / RequestParam 사용
- Controller 분리 구조
