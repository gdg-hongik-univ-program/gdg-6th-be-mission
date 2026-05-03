#소비자 (User)
1. 상품조회
- Method: GET
- URL: /products/{productId}
- Input X (Request X)
- Output(Response O)
  {
      "productId": "num1",
      "productName": "lemonJuice",
      "productPrice": 2000,
      "remainQuantity": 50
  }

2. 상품 구매
- Method: POST
- URL: /orders
- Input (Request O)
  {
      "orderProducts": [
      {
          "productId": "num1",
          "quantity": 5
      }
     ]
  }
-Output (Response O)
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

#관리자 (Admin)
1. 상품 등록
- Method: POST
- URL: /products
- Input O (Request O)
