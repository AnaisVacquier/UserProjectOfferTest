# UserProjectOfferTest
This API contains 2 REST services: one that allows to register a user and the other one that displays the details of a registered user.

Requirements:
- define a user (what are the fields needed). We should have mandatory and optional fields!
- validate the input and return proper error messages/http status
- log the input and output of each call and the processing time.
- have a request parameter which is not mandatory and which provides a default value in case is not set
- have a path variable
- clear code and javadoc
- unit tests
- only adults ( age > 18 years)  and that live in France can create an account!


Bonuses:
- user a non-relational DB in order to save the users!
- use AOP
- documentation/UML/schemas to explain the architecture


This API uses SpringBoot, JAVA8, a MongoDB server, the unit tests are done using JUnit and the dependencies are managed by Maven.


Here's an example of the JSON to send (with Postman for example) in order to register a user :
{
    "firstName": "Remy",
    "lastName": "FASOL",
    "age": "34",
    "email": "r.fasol@gmail.com",
    "phoneNumber": "0697586236",
    "address": {
        "street": "1200 Route des Lucioles",
        "zipCode": "06560",
        "city": "valbonne",
        "country": "France"
    }
}
