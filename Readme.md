# API Documentation

Use cURL in bash only 

## 1. Upload CSV API

*Description:*  
Upload a CSV file containing product data. Valid rows will be saved, and invalid rows will return error messages.


*Endpoint:*  
POST /api/products/upload

*Request Example (cURL):*
curl -X POST -F "file=@products.csv" http://localhost:8080/api/products/upload


*Successful Response Example:*
{"stored":20,"failed":1}


---

## 2. List Products API

*Description:*  
Retrieve the list of all stored products.

*Endpoint:*  
GET /api/products

*Request Example (cURL):*
curl http://localhost:8080/api/products


*Response Example:*
[
    {"sku":"BAG-TOTE-BEI","name":"Canvas Tote Bag","brand":"CarryCo","color":"Beige","size":"OneSize","mrp":899,"price":699,"quantity":35},{"sku":"BELT-BRN-38","name":"Leather Belt","brand":"CarryCo","color":"Brown","size":"38","mrp":1199,"price":899,"quantity":40},{"sku":"DRESS-PNK-S","name":"Floral Summer Dress","brand":"BloomWear","color":"Pink","size":"S","mrp":2499,"price":2199,"quantity":10},{"sku":"DRESS-YLW-M","name":"Floral Summer Dress","brand":"BloomWear","color":"Yellow","size":"M","mrp":2499,"price":1999,"quantity":7},{"sku":"HOODIE-CHR-XL","name":"Cozy Hoodie","brand":"SnugWear","color":"Charcoal","size":"XL","mrp":2199,"price":1799,"quantity":11},{"sku":"HOODIE-CRM-M","name":"Cozy Hoodie","brand":"SnugWear","color":"Cream","size":"M","mrp":2199,"price":1699,"quantity":9},{"sku":"JEANS-BLK-030","name":"Slim Fit Jeans","brand":"DenimWorks","color":"Black","size":"30","mrp":1999,"price":1499,"quantity":18},{"sku":"JEANS-BLU-032","name":"Slim Fit Jeans","brand":"DenimWorks","color":"Blue","size":"32","mrp":1999,"price":1599,"quantity":15},{"sku":"JKT-OLV-L","name":"Utility Jacket","brand":"UrbanEdge","color":"Olive","size":"L","mrp":3499,"price":2999,"quantity":6},{"sku":"KURTA-BLU-M","name":"Cotton Kurta","brand":"Ethniq","color":"Blue","size":"M","mrp":1599,"price":1299,"quantity":22},{"sku":"POLO-GRN-003","name":"Heritage Polo","brand":"StreamThreads","color":"Green","size":"XL","mrp":1299,"price":999,"quantity":8},{"sku":"SAREE-RED-001","name":"Banarasi Silk Saree","brand":"Ethniq","color":"Red","size":"Free","mrp":6999,"price":5999,"quantity":5},{"sku":"SHIRT-CHK-M","name":"Checked Casual Shirt","brand":"ButtonUp","color":"Multi","size":"M","mrp":1799,"price":1399,"quantity":16},{"sku":"SHIRT-PLN-L","name":"Plain Oxford Shirt","brand":"ButtonUp","color":"Blue","size":"L","mrp":1899,"price":1499,"quantity":12},{"sku":"SHOE-NVY-8","name":"Everyday Sneakers","brand":"StrideLab","color":"Navy","size":"UK8","mrp":2999,"price":2499,"quantity":19},{"sku":"SHOE-WHT-7","name":"Everyday Sneakers","brand":"StrideLab","color":"White","size":"UK7","mrp":2999,"price":2499,"quantity":25},{"sku":"TSHIRT-BLK-002","name":"Classic Cotton T-Shirt","brand":"StreamThreads","color":"Black","size":"L","mrp":799,"price":549,"quantity":12},{"sku":"TSHIRT-GRY-S","name":"Graphic Tee","brand":"UrbanEdge","color":"Grey","size":"S","mrp":899,"price":699,"quantity":30},{"sku":"TSHIRT-RED-001","name":"Classic Cotton T-Shirt","brand":"StreamThreads","color":"Red","size":"M","mrp":799,"price":499,"quantity":20},{"sku":"TSHIRT-WHT-XS","name":"Graphic Tee","brand":"UrbanEdge","color":"White","size":"XS","mrp":899,"price":649,"quantity":14}
    
]


---

## 3. Search Products API

*Description:*  
Search products by filter parameters like brand, color, and price range.
search products by filter parameters minprice or maxprice

*Endpoint:*  
GET /api/products/search?brand=<brand>&color=<color>&minPrice=<min>&maxPrice=<max>

*Request Example (cURL):*
curl "http://localhost:8080/api/products/search?brand=BloomWear&maxPrice=2500"

[
   {"sku":"DRESS-PNK-S","name":"Floral Summer Dress","brand":"BloomWear","color":"Pink","size":"S","mrp":2499,"price":2199,"quantity":10},{"sku":"DRESS-YLW-M","name":"Floral Summer Dress","brand":"BloomWear","color":"Yellow","size":"M","mrp":2499,"price":1999,"quantity":7}
]


---

### Notes:
- Replace http://localhost:8080 with your server's actual URL and port if different.
- Use the Upload API to insert products, then List or Search to verify and retrieve data.
- The partial failure response helps identify rows that failed during CSV upload.
- Back button take to  the previous page which filtered or uploaded
- Home button take to main page 
- Can use any format type in search filter, it converts in lower case and trialing spaces# streamoid
