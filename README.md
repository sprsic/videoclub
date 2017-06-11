   
**Video rental store** <br/>

Sample implementation of CRUD operation for some Video rental store and price calculation based on lease duration.

For this implementation Spring boot will be used because it gives out off the box all that it is needed for creating web 
application guided by  convection the over configuration with zero xml code.
With embedded tomcat container and h2 database. Purpose of this implementation is to show some basic operation of the rental 
store price calculation and exposing some REST endpoints.
Other configurations (like server configuration, transaction configuration, hibernate configuration, connection pool...) are set on default values 
,  and they are not subject of this implementation.
In order to keep it simple there will be no keeping track of available copies of movie for rent so that in the 
multithreaded environment accessing shared resource like db will have dirty read and write .



