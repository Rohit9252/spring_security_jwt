# Spring Security JWT Application

This is a sample Spring Boot application that demonstrates how to implement JWT-based authentication and authorization using Spring Security. This application is designed to be deployed on Docker.

# Getting Started
To run this application on Docker, you'll need to have Docker installed on your machine. You can download and install Docker from the official Docker website.

To build and run the Docker image for this application, follow these steps:

1.Clone the repository to your local machine.

  * git clone https://github.com/rohit9252/spring_security_jwt.git
 
2. Change into the project directory. 

   * cd spring-security-jwt-app
  
3. Build the Docker image.

  * docker build -t spring-security-jwt-app 

4. Run the Docker container.
 
 # docker run --network loginnetwork  --name security  -p 8888:8080 jwtapp  
 
 # docker-compose up --build  
 
Once the container is running, you can access the application at http://localhost:8080.

# API Endpoints
The following endpoints are available in this application:

* `/api/login - Authenticates a user and returns a JWT token
* `/api/signup - Registers a new user
* `/api/hello - Get user details
* `/api/all - A public endpoint that can be accessed without authentication
* `/api/user - An endpoint that can only be accessed by authenticated users
* `/api/admin - An endpoint that can only be accessed by users with the "ROLE_ADMIN" role


# Configuration
The application uses the following configuration properties:
 
* `jwt.secret - The secret key used to sign JWT tokens
* `jwt.expirationMs - The expiration time (in milliseconds) for JWT tokens
* `spring.datasource.url - The URL of the database used by the application
* `spring.datasource.username - The username used to connect to the database
* `spring.datasource.password - The password used to connect to the database


You can modify these properties by editing the application.properties file located in the src/main/resources directory.

# Security

This application uses Spring Security to provide authentication and authorization. JWT tokens are used to authenticate users and to grant access to protected endpoints.


# License

This project is licensed under the MIT License. See the LICENSE file for details.

# Acknowledgments

This application was created using the following technologies:

 * Spring Boot
 * Spring Security
 * JWT
 * MySQL
 * Maven
 * Docker

#  For shut down the Application

 * docker-compose down 
 
 
#  Thank You !! 😊😊
  
  

  



   
   

