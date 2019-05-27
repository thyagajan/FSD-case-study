FROM java:8-jre
WORKDIR /usr/lib
ENV FAV_MONGO_DB_NAME=favouritesdb
ENV FAV_MONGO_DB_URL=mongodb://localhost:27017/favouritesdb
ADD ./target/favouriteservice-1.0.jar /usr/lib/favouriteservice-1.0.jar
ENTRYPOINT ["java","-jar","favouriteservice-1.0.jar"]