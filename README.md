Users is a Rest API that allows operations CRUD (Create, Read, Update, Delete) for users. It is built using java 17 and Spring Boot 3.0.0

## API Endpoints

The following endpoints are available for the Users API:

- `GET /user` - Retrieve a list of all users.
- `GET /user/{id}` - Retrieve a specific user by ID.
- `POST /user` - Create a new user.
- `PUT /user/{id}` - Update an existing user by ID.
- `DELETE /user/{id}` - Delete an existing user by ID.

## Installation

To install and run Users API locally, follow these steps:

1. Clone the repository to your local machine :
```
git clone https://github.com/MayaOrange/users.git
```
3. Install the required dependencies:
```
cd user
mvn clean install
```
4. Start the server:
```
mvn sprint-boot:run 
or 
cd targer
java -jar users-0.0.1-SNAPSHOT.jar
```
The API will be available at http://localhost:9000.
The Embeded Database H2 will be available at http://localhost:9000/h2-console 
```
Starting UsersApplication using Java 17.0.6 with PID 26984 
Tomcat initialized with port(s): 9000 (http)
H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:e4bdae8d-7b5d-4a52-bc96-d5aa898776d7'

```
## Usage
A collection postman is available in project:
```
\users\src\main\resources\postman\users.postman_collection.json
```

- To retrieve a list of all users:

GET http://localhost:9090/user

Example of results:
```
[
    {
        "id": 1,
        "name": "Maya",
        "dateBirth": "1979-07-12",
        "countryResidence": "France",
        "phoneNumber": "06 01 01 01 01",
        "gender": "FEMALE"
    },
    {
        "id": 2,
        "name": "Robert",
        "dateBirth": "1959-07-12",
        "countryResidence": "France",
        "phoneNumber": "0654765543",
        "gender": "MALE"
    },
        {
        "id": 3,
        "name": "Angel",
        "dateBirth": "1980-12-12",
        "countryResidence": "France",
        "phoneNumber": "0654765543",
        "gender": "MALE"
    }
]
```

- To retrieve a specific user by ID:

GET http://localhost:8080/users/1

Example of result :

```
{
    "data": {
        "id": 1,
        "name": "Maya",
        "dateBirth": "1979-07-12",
        "countryResidence": "France",
        "phoneNumber": null,
        "gender": "UNKOWN"
    },
    "errors": []
}
```

- To create a new user:

POST http://localhost:8080/users
Content-Type: application/json

Only residents adult French residents can create an account.
```
{
 "name": "Robert",
 "dateBirth": "12/07/1979",
 "countryResidence": "France",
 "phoneNumber": "0654765543",
 "gender": "Male"
 }
 ```
 Controls are :
```
  Name is a mandatory field and cannot be null or empty. 
  Name must be 50 characters maximum.
 
  Date of birth must be in the formats dd/MM/yyyy | dd-MM-yyyy | yyyy/MM/dd | yyyy-MM-dd.
  User must reach the age of majority witch is 18 years

  France is an optional field 
  France is the only Country of Residence accepted. Allowed values: FR, FRANCE, France.

   Gender is an optional Gender (Female/Male/Non-Binary)
```

- To update an existing user by ID:

PUT http://localhost:8080/users/1
Content-Type: application/json

```
{
 "name": "Robert Dagobert",
 "dateBirth": "12/07/1945",
 "countryResidence": "France",
 "phoneNumber": "0654765543",
 "gender": "Male"
 }
```
- To delete an existing user by ID:

DELETE http://localhost:8080/users/1
## DONE LIST (ce que j'ai pu réaliser)
- La séparation des responsabilités (controleur/service/repo).
- Utilisation d'une db H2 embarquée, ainsi que spring data.
- Mise en place des TU/TI.
- Documentation du code.
- Utilisation de Lombok (@Data)
- Validation par annotations.
- Création d'annotations spécifiques : @CountryFr @AdultUserFr @PhoneNumberFr etc  
- Utilisation controleurAdvice pour gérer les exceptions 

## TODO LIST /Axe d'améliorations
Par manque de temps, certains points n'ont pas été traités :
- Mise en place de AOP Log
- Mise en place MapStruct pour faciliter les mappings
- Tests d'intégrations pour les cas déclenchant une exception fonctionnelle

