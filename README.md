# Expense-Tracker API

Learning how to use Java Spring Boot with PostgreSQL and JWT to create a REST API.

- Clone the repository and navigate to the expense-tracker directory.

```
git clone https://github.com/akhilmakes/expensetrackerapi.git
cd expense-tracker

```


- Install PostgreSQL on your system [here](https://www.postgresql.org/download) , or if you have Docker installed run the command below to create a database instance:

```
docker container run --name postgresdb -e POSTGRES_PASSWORD=admin -dp 5432:5432 postgres
```

- In the root directory(expense-tracker) there is an SQL script file(expensetracker_db.sql) present for creating the database objects.
 - If using docker (else skip this step), copy this file into the running container and then execute the script using the following commands:

 ```
 docker container cp expensetracker_db.sql postgresdb:/
 docker container exec -it postgresdb psql -U postgres --file expensetracker_db.sql
 ```

- If your database is hosted on a different platform, or you have made changes to the SQL script file. Ensure you update the src/main/resources/application.properties file accordingly.

```
spring.datasource.url=jdbc:postgresql://localhost:5432/expensetrackerdb
spring.datasource.username=expensetracker
spring.datasource.password=password

```

- Then run the Spring Boot application:

```
mvnw spring-boot:run

```

Credits: PairLearning
