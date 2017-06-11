   
**Video rental store** <br/>

Sample implementation of CRUD operation for some Video rental store and price calculation based on lease duration.

For this implementation Spring boot will be used because it gives out off the box all that it is needed for creating web 
application guided by  convection the over configuration with zero xml code.
With embedded tomcat container and h2 database. Purpose of this implementation is to show some basic operation of the rental 
store price calculation and exposing some REST endpoints.
Other configurations (like server configuration, logging, transaction configuration, hibernate configuration, connection pool...) are set on default values 
,  and they are not subject of this implementation.
In order to keep it simple there will be no keeping track of available copies of movie for rent so that in the 
multithreaded environment accessing shared resource like db will have dirty read and write.


There is already init db script with sql that will prepopulate db in resources/data.sql
with employee, customer, movie, genre, movie_genre

**Endpoints** <br/>

**http://localhost:8080/lease** method: POST
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
response body sample:
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

**http://localhost:8080/overDue/lease/2** method: GET
response body sample:
```javascript
{
    "success": true,
    "message": "OK",
    "data": {
        "leasePrices": [],
        "total": 0,
        "leaseId": 2
    }
}
```
**http://localhost:8080/customer/2/bonus** method: GET
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
{
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
