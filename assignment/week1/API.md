# 📦 API 명세
---
## 👤 소비자 (User)

### 1. 상품조회
- Method: GET
- URL: /products/{productId}
- Input X (Request X)
- Output(Response O)
```json
  {
      "productId": "num1",
      "productName": "lemonJuice",
      "productPrice": 2000,
      "remainQuantity": 50
  }
```

### 2. 상품 구매
- Method: POST
- URL: /orders
- Input (Request O)
```json
  {
      "orderProducts": [
      {
          "productId": "num1",
          "quantity": 5
      }
     ]
  }
```
- Output (Response O)
```json
  {
      "orderId": "Ord1",
      "price" : 10000,
      "orderProducts": [
      {
          "productName": "lemonJuice",
          "quantity": 5,
          "amount": 10000
      }
     ]
  }
```

## 관리자 (Admin)

### 1. 상품 등록
- Method: POST
- URL: /products
- Input O (Request O)
```json
  {
      "productName": "appleJuice",
      "productPrice": 2500,
      "remainQuantity": 60
  }
```
- Output O (Respone 성공시)
```json
  {
      "productId": "num2",
      "productName": "appleJuice",
      "productPrice": 2500,
      "remainQuantity": 60
  }
```
- Input O (Request O but 잘못된 요청일 경우)
```json
  {
      "productName": "lemonJuice",
      "productPrice": 2500,
      "remainQuantity": 60
  }
```
- Output O (Response 실패-> 중복 이름을 가진 상품 존재)
```json
   {
      "error": "상품이 이미 존재합니다"
   }
```

### 2. 재고 추가
- Method: PATCH
- URL: /products/{productId}
- Input O (Request O)
```json
  {
      "productId": "num2",
      "addQuantity": 20
 }
```
- Output O (Response O)
```json
  {
      "productName": "appleJuice",
      "remainQuantity": 80
  }
```
### 3. 상품 삭제
- Method: DELETE
- URL: /products
- Input O (Request O)
```json
  {
      "productsIds": [ "num1"]
  }
```
- Output O (Response O)
```json
{
      "products": [
      {
          "productName": "appleJuice",
          "remainQuantity": 80
      }
     ]
 }
```
  
