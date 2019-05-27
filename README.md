# CMatches - A Case Study(FSD SBA case study)

## About the Application

Cricket is one of international sport and it is widely popular in India.
 
This case study is about showing cricket matches played around the world. Also it displays the cricket score for the past matches.

CMatches application allows the user to find cricket matches, bookmark favourite matches and recommend most liked/favourite matches to user.

## Thirdparty API
This application uses the following api provider to find the cricket matches and the score details
https://www.cricapi.com/


## Tech Stack

- Spring Boot
- API Gateway
- Message Broker (RabbitMQ)
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose

## Modules

- CMatchesUI frontend: Runs on Port 8080
    
    - http://localhost:8080
    - Built on Angular 7 and angular material components.
    - UI is responsive and runs smoothly on different mobile and IPAD devices. 
    - Using 'Angular flex layout' to achieve responsiveness.
    - Image is availale on docker hub (tnagaraj/matchesuiapp:v1)
    - It has the following views 
    
        - Register 
            - Allows the user to register themself with First Name, Last name, user name and password.
    
        - Login 
            - Allows the user to login to the application.
    
        - Matches 
            - Shows the current and next matches using cricapi.com. 
            - It displays the matches in list format and can be filtered by given date to view the cricket calendar. 
            - allows to display the cricket score(if available) 
            - user can bookmark a particular match.
            
        - Favourites 
            - displays the user's favorites in a list view 
            - has the capability to filter the matches by a date. 
            - allows user to remove the matches from the favourites
            - view cricket score.
            
        - recommendations
            - displayes the cricket matches in the order of most liked by the users including the current user. 
            - Allows the user to add it to their favourites.
            - Allows the user to view the score
            - Filter by a specific date.
            
        - Logout
            - Redirects to Login component.
            
     

- UserService(Port :8081)

    - Uses Spring boot, JWT and MYSQL.
    - Runs on port 8081.
    - has registration and login operations.
    - Image is available on docker hub(tnagaraj/userservice:v1)
    - More documentation can be found at 
        http://localhost:8081/swagger-ui.html.


- FavouriteService(Port : 8082)

    - Built on Spring boot, MongoDB. 
    - Runs on port 8082
    - Produces the match object to recommendation service using Rabbit MQ.
    - Image is available on docker hub(tnagaraj/favouriteservice:v1)
    - More documentation can be found about the API at
        http://localhost:8082/swagger-ui.html
        
  

- MatchRecommendationService(Port : 8084)

    - Built on Spring boot, MongoDB. 
    - Runs on port 8084
    - Consumes the match object from favourite service using Rabbit MQ.
    - Image is available on docker hub(tnagaraj/matchrecommendationservice:v1)
    - More documentation can be found about the API at
        http://localhost:8084/swagger-ui.html
    
- Eureka Server (Port :8083)

    - Netflix's discovery service
    - userservice, favouritesservice and matchrecommendationservice are registered as clients.
    - Image is availabr on docker hub(tnagaraj/eurekaserver:cmatch)

- Zuulservice( Port : 8085)

    - Netflix's proxy service.
    - Used by UI as a single service to connect to different microservices.
    - Image is available on docker hub(tnagaraj/zuulservice:cmatch)
    - Uses filter to verify the JWT token.
    
    
## How to run the application.
Since both the frontend and backend application are dockerized, it is pretty simple to run the application.
Download the application from git lab and run the following command at the root directory

docker-compose up.

access the application at 
http://localhost:8080


