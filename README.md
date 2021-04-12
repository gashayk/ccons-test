# ccons-test

The application on bootstrap will create and initilize the H2 database.
For sql script versioning Flyway has been used. Any changes on the script should be done int new script file with proper naming.

To run the application follow below described steps:
  1. Download the project from master branch
  2. Unzip the file and ch to root directory (cd ccons-test-master)
  3. Execute "mvn package" to generate the jar file or "mvn package -DskipTests" to skip tests
  4. Cd to /target folder
  5. Execute the command to run the applicaiton: java -jar app-0.0.1-SNAPSHOT
  
NOTE: Alternatively after downloading the project import it in your IDEA of choice and run the application.

To access the Swagger interface after bootstraping the application access the following link:
  http://localhost:8080/swagger-ui.html#/product-price-controller
  
To access to H2 console access the follow link, no password required:
  http://localhost:8080/h2

