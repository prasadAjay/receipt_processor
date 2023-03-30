Receipt Processor
This project is written in Java using spring boot framework, in-memory H2 database and docker for hosting the image container.
This project fulfils the documented API in api.yml.
This project hits the two important APIs - POST and GET APIs.

Testing:
Let's use Docker to run this project. Please install the Docker on your local machine if it is not installed.

Step 1: Pull an image from the Docker Hub:
```docker pull prasadaj/receiptprocessor:rptest```

Step 2: Create and run a new container from the image that you just pulled
```docker run --name rptest -d -p 8080:8080 prasadaj/receiptprocessor:rptest```

Step 3: Check if the container is running
```docker ps | grep prasadaj/receiptprocessor:rptest```

Step 4: Make a note of the Container ID:

Step 5: Send the POST Request to the Server by hitting the POST API
```curl http://localhost:8080/receipts/process -X POST -H "Content-Type:application/json" -d '{"retailer":"M&M Corner Market","purchaseDate":"2022-03-20","purchaseTime":"14:33","items":[{"shortDescription":"Gatorade","price":"2.25"},{"shortDescription":"Gatorade","price":"2.25"},{"shortDescription":"Gatorade","price":"2.25"},{"shortDescription":"Gatorade","price":"9.00"}],"total":"9.00"}'```

Step 6: The POST Request returns an ID. Make a note of this ID, this ID is required to send the GET Request

Step 7: Send the GET Request to the Server by hitting the GET API
```curl  http://localhost:8080/receipts/<id-from-step-6-POST-API>/points```

Step 8: Stop docker container
```docker stop <container-id-from-step-3>```

Testing Screenshot for reference. I have used POSTMAN application to test the POST and GET APIs:
Step 1: POST API - http://localhost:8080/receipts/process

Examples

Input:

```
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}
```

Output:

![POST 1](https://drive.google.com/uc?export=view&id=1C8bMWIkq7ycAlPno75MTDL6qiCDeDlYW)

Step 2: GET API - http://localhost:8080/receipts/{id}/points

Output:

![GET 1](https://drive.google.com/uc?export=view&id=149REwkqIYLXUhqBdQKY0ekSzJBirS61E)

Repeat Steps 1 & 2:

Input:

```
{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}
```

Output:

![POST 2](https://drive.google.com/uc?export=view&id=1ojoQ8b9XFwjkVXihmwpm1itDO9Z1r0kF)

Step 2: GET API - http://localhost:8080/receipts/{id}/points

Output:

![GET 2](https://drive.google.com/uc?export=view&id=15zEsoAd-IibyEg5C5_P8DdxUt_QNufn7)

