# Estate

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.0.

## Start the project

Git clone:

> git clone https://github.com/OpenClassrooms-Student-Center/P3-Full-Stack-portail-locataire

Go inside folder:

> cd P3-Full-Stack-portail-locataire

Install dependencies:

> npm install

Launch Front-end:

> npm run start;


## Ressources

### Mockoon env

Download Mockoon here: https://mockoon.com/download/

After installing you could load the environement

> ressources/mockoon/rental-oc.json

directly inside Mockoon 

> File > Open environmement

For launching the Mockoon server click on play bouton

Mockoon documentation: https://mockoon.com/docs/latest/about/

### Postman collection

For Postman import the collection

> ressources/postman/rental.postman_collection.json 

by following the documentation: 

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman


### MySQL

SQL script for creating the schema is available `ressources/sql/script.sql`

###  API documentation

Before starting the project, you will need to run a database for it to work. Several approaches are possible, but the one I recommend is to ensure you have Docker 
installed on your machine and execute the following command (you can use any database credentials, 
but make sure to use the exact same ones in the .env file you will create. There is a .env.example file provided for reference):
```docker run --name chatop-postgres -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=user -e POSTGRES_DB=chatop -p 5432:5432 -d postgres
```

Remember to assign a secret key to the JWT_SECRET environment variable.
THE SECRET MUST BE AT LEAST 256 BITS LONG TO ENSURE JWT SECURITY.

If you don't, you will encounter this error:
```An error occurred while attempting to decode the Jwt: The secret length must be at least 256 bits```

To generate a secret, you have several options. The simplest is to enter this command in your terminal:

```node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"``` or ```openssl rand  -base64 32``` or visit https://www.grc.com/passwords.htm



