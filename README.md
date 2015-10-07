# Users-Management-Rest-API
REST API's for Authentication and CRUD operations on Users and their information data

Technologies used :
Jersey framework - Based on JAX-RS specifications
Eclipse IDE, Maven Build

Steps to run :
1. Convert to Maven project
2. Right click project  Runas  Run on Server

The project deployed on TomCat server 7 for development
MySql database : Amazon RDS for persistence
REST API's developed :

API 1 : Create a new user
http://localhost:8080/UsersManagement/rest/users/
Method Type : POST
{
"userName" : "root",
"password" : "123_Welcome"
}
Response : Status : 200 OK
Response :
{
"message" : "User with user name root created"
}

http://localhost:8080/UsersManagement/rest/users/
Method Type : POST
{
"userName" : "samarth",
"password" : "123_Welcome"
}

Response :
Status : 200 OK
{
"message" : "User with user name samarth created"
}

Note : If the user already exists in the system we get :

Response :
Status : 400 Bad Request
{
"message" : "User with user name samarth created"
}
API 2 : Get the created users
http://localhost:8080/UsersManagement/rest/users/

Method Type : GET

Response :
Status : 200 OK

{
usersList: [2]

0: "root"
1: "samarth"
-}

If the user credentials are not of the root user,
Status : 401 Authorization Required
{
"message" : "Invalid user credentials "
}

API 3 : Add user information
http://localhost:8080/UsersManagement/rest/users/information/
Method Type : PUT

{
"firstName" : "Samarth",
"lastName" : "Dhingra",
"country" : "US",
"creditCardNumber" : "12345678"
}

Response :
Status : 200 OK

{
"message" : "Updated information for the user "
}

If the credentials are invalid we get,

Status : 401 Authorization Required
{
"message" : "Invalid user credentials "
}

API 4 : Delete user information
http://localhost:8080/UsersManagement/rest/users/information/country
Method Type : DELETE
Response :
Status : 200 OK
{
"message" : "Information field value of country deleted successfully. "
}

API 5 : Add user information for a granular information type
http://localhost:8080/UsersManagement/rest/users/information/country
Method Type : PUT
{
"country" : "India"
}

Response :

Status : 200 OK
{
"message" : "Information field value of country updated successfully. "
}

Improvements :

- Can use OAuth : Open Standard for Authorization
- Use hashing to store passwords
- Alternate schema design

Another way to store :

{
"PersonalInfo" :
[
{
"InfoType" : "firstName",
"value" : "Samarth"
},

{
"InfoType" : "lastName",
"value" : "Dhingra"
}
]
}
- Advantage : Can add new information types like age, address for a user without redesigning the schema. New information types will be added as rows of the database instead of the columns

- Disadvantage : We will need to hard code the value of the information to string
