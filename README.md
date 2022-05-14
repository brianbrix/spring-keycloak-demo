## Spring Boot/ Keycloak Authorization
- [ ] This is a simple containerized spring boot application to demonstrate  usage of Keycloak as **Authentication** and **Authorization** server with OpenId Connect protocol

The application is made up of majorly two services:
1. Keycloak server
2. Simple Spring Boot REST APIs.

### To run the app:
1. #### Install docker and docker-compose
2. #### Run docker-compose up --build - This will start the keycloak server and the application itself.
3. Open the keycloak admin panel at [This link](https://localhost:8443/)
4. Follow the instructions on [this link](https://www.baeldung.com/spring-boot-keycloak) to set up a keycloak realm, role, user and some few other identity specifications:
- For this app we use the following settings:
- [ ] Realm: test
- [ ] Role: user
- [ ] User: user1
- [ ] Client: SpringBootKeyCloak

To get access_token: Make post request with json credentials to http://localhost:8082/token.

To test the token received: make GET request to http://localhost:8082/api