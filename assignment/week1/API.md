##1. 상품 조회

Method: GET

URL: /products/{name}

Request: PathVariable

Response:

```json
{
 "id": 2,
 "name": "버즈",
 "price": 129000,
 "stock": 8
}
```

##2. 상품 구매

Method: POST

URL: /buy

Request:

```json
[
 {
   "id": 1,
   "quantity": 2
 },
 {
   "id": 2,
   "quantity": 1
 }
]
```

Response:

구매 완료 / 총 금액: 627000

##3. 상품 등록

Method: POST

URL: /admin/product

Request:

```json
{
 "name": "에어팟",
 "price": 249000,
 "stock": 5
}
```

Response:

```json
{
 "id": 1,
 "name": "에어팟",
 "price": 249000,
 "stock": 5
}
```

##4. 재고 추가

Method: PATCH

URL: /admin/product/{id}/stock?quantity=3

Request:

id: 상품 ID (Long)

quantity: 추가 재고 수량 (int)

Response:

```json
{
 "id": 1,
 "name": "에어팟",
 "price": 249000,
 "stock": 8
}
```

##5. 상품 삭제

Method: DELTE

URL: /admin/products

Request:

```json
[1, 2]
```

Response:

```json
[]
```