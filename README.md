   
**Video rental store** <br/>

Sample implementation of CRUD operation for some Video rental store and price calculation based on lease duration.

Main components are divided into several packages.<br/>
**com.sprsic.entity**: contains all orm mappings for the underlying h2 database, and on the application startup the hibernate(JPA) will
generate db schema based on the classes in the entity package.<br/>
**com.sprsic.dao**: contains repository methods for accessing entities, using spring data there is no need for the implementation of 
the methods definition because spring data is smart enough to create queries based on the method name (isn't that awesome :) ).<br/>
**com.sprsic.model** and **com.sprsic.model.common**: contains POJOs for mapping requests/responses<br/>
**com.sprsic.service**: contains all the business logic for the rental store.<br/>
**com.sprsic.resource**: contains endpoint mappings.<br/>
**com.sprsic.validator**: contains validators for validating input received from the client<br/>
**com.sprsic.util**: contains  util methods. <br/>

Resources folder contains application properties and also data.sql that will be inserted when application starts.

**Endpoints** <br/>

**http://localhost:8080/lease** method: POST <br/>
request body sample:
```javascript
{
"employeeId": 1,
  "customerId": 2,
  "leaseDate": "2017-06-11",
  "movies": [{
    "returnDate": "2017-06-13",
    "movieId": 1
  }]
}
```
<br/>response body sample:
```javascript
{
    "success": true,
    "message": "OK",
    "data": {
        "leasePrices": [
            {
                "movieName": "Some new movie",
                "movieType": "NEW_RELEASE",
                "priceRent": 80
            }
        ],
        "total": 80,
        "leaseId": 2
    }
}
```

**http://localhost:8080/customer/lease/1/overDueSurcharges** method: GET //calculate based on a difference of current date and return date when rented
<br/>response body sample:
```javascript
{
    "success": true,
    "message": "OK",
    "data": {
        "leasePrices": [
            {
                "movieName": "Some new movie",
                "movieType": "NEW_RELEASE",
                "priceRent": 80
            },
            {
                "movieName": "Some regular movie",
                "movieType": "REGULAR_FILM",
                "priceRent": 30
            }
        ],
        "total": 110,
        "leaseId": 1
    }
}
```
**http://localhost:8080/customer/2/bonus** method: GET<br/>
response body sample:
```javascript
{
    "success": true,
    "message": "OK",
    "data": {
        "bonusPoints": 4
    }
}
```

**http://localhost:8080/customer/2/bonusHistory** method: GET
<br/>
```javascript
{
    "success": true,
    "message": "OK",
    "data": {
        "bonusHistory": [
            {
                "id": 1,
                "customerId": 2,
                "bonusAmount": 2,
                "createdDate": "2017-06-11 05:59:23"
            },
            {
                "id": 2,
                "customerId": 2,
                "bonusAmount": 2,
                "createdDate": "2017-06-11 06:09:38"
            }
        ]
    }
}
```
<br/>
If you have some questions or find a bug, contact me s.prsic[at]gmail.com