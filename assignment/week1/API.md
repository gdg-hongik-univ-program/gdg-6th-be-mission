## **소비자 (User)**

### **1. 상품 조회**

특정 상품명으로 상품 정보를 조회한다. (동일한 상품명은 하나만 존재한다고 가정)

응답 정보 : 상품 ID, 상품명, 가격, 재고 수량

응답에 상품 ID가 포함되기 때문에, 이후 구매 시 **해당 ID**를 사용할 수 있어요.

- HTTP Method : GET
- URL : /products
- Input (Request) : name=사과 (쿼리 파라미터)
- Output (Response) :

조회 성공 시 : 200 OK

```json
{
	"id" : 1,
	"name" : "사과",
	"price" : 10000,
	"stock" : 100
}
```

존재하지 않는 상품 조회 시 : 404 Not Found

```json
{
	"errorCode" : "PRODUCT_NOT_FOUND",
	"message" : "존재하지 않는 상품입니다.",
	"id" : 1
}
```

### **2. 상품 구매**

하나 이상의 상품을 한 번에 구매한다.

요청 정보 : 상품 ID, 구매 수량 (여러 상품 가능)

응답 정보 : 총 구매 금액, 구매한 상품 목록 (상품명 / 구매 수량 / 해당 상품 소비 금액)

- HTTP Method : POST
- URL : /orders
- Input (Request)

```json
{
	"orderProducts" : [
		{
			"id" : 1,
			"quantity" : 4
		},
		{
			"id" : 2,
			"quantity" : 3
		}
	]
}
```

- Output (Response) :

주문 성공 시 : 201 Created

```json
{
	"totalPrice" : 100000,
	"orderProducts" : [
		{
			"id" : 1,
			"name" : "사과",
			"quantity" : 4,
			"subtotalPrice" : 40000
		},
		{
			"id" : 2,
			"name" : "배",
			"quantity" : 3,
			"subtotalPrice" : 60000
		}
	]
}
```

존재하지 않는 상품 주문 시 : 404 Not Found

```json
{
	"errorCode" : "PRODUCT_NOT_FOUND",
	"message" : "존재하지 않는 상품입니다.",
	"id" : 2
}
```

재고가 부족한 경우 : 409 Conflict

```json
{
  "errorCode" : "OUT_OF_STOCK",
  "message" : "재고가 부족합니다.",
  "productId" : 1
} 
```

## **관리자 (Admin)**

### **1. 상품 등록**

새로운 상품을 등록한다. 동일한 이름의 상품이 이미 존재하면 에러를 반환한다.

요청 정보 : 상품명, 개당 가격, 초기 재고 수량

- HTTP Method : POST
- URL : /products
- Input (Request)

```json
{
	"name" : "포도",
	"price" : 5000,
	"stock" : 100
}
```

- Output (Response)

등록 성공 시 : 201 Created

```json
{
	"id" : 3,
	"name" : "포도",
	"price" : 5000,
	"stock" : 100
}
```

등록 실패 시 : 409 Conflict

```json
{
	"errorCode" : "ALREADY_EXISTS",
	"message" : "이미 존재하는 상품입니다.",
	"target" : "포도"
}
```

### **2. 재고 추가**

기존 상품의 재고를 추가한다.

요청 정보 : 상품 ID, 추가할 수량

응답 정보 : 상품명, 최종 재고 수량

- HTTP Method : PATCH
- URL : /products/{id}/stock
- Input (Request)

```json
{
	"additionalQuantity" : 50
}
```

- Output (Response)

추가 성공 시 : 200 OK

```json
{
	"id" : 3,
	"name" : "포도",
	"stock" : 150
}
```

존재하지 않는 id일 경우 : 404 Not Found

```json
{
	"errorCode" : "PRODUCT_NOT_FOUND",
	"message" : "존재하지 않는 상품입니다.",
	"id" : 3
}
```

### **3. 상품 삭제**

상품을 삭제합니다. 한 번에 여러 개도 삭제할 수 있어요.

요청 정보 : 삭제할 상품 ID 목록

응답 정보 : 현재 남아있는 상품 목록 (상품명 / 재고 수량)

- HTTP Method : Delete
- URL : /products
- Input (Request)

```json
{
	"productIds" : [1, 2]
}
```

- Output (Response)

삭제 성공 시 : 200 OK

```json
{
	"remainProducts" : [
		{
			"id" : 3,
			"name" : "포도",
			"stock" : 150
		}
	]
}
```

삭제 실패 시 : 404 Not Found

```json
{
	"errorCode" : "PRODUCT_NOT_FOUND",
	"message" : "삭제하려는 상품 중 일부가 존재하지 않습니다.",
	"id" : 3
}
```