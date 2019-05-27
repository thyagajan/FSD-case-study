FROM java:8-jre
WORKDIR /usr/lib
ENV MR_MONGO_DB_NAME=recommendationdb
ENV MR_MONGO_DB_URL=mongodb://localhost:27017/recommendationdb
ADD ./target/matchrecommendationservice-1.0.jar /usr/lib/matchrecommendationservice-1.0.jar
ENTRYPOINT ["java","-jar","matchrecommendationservice-1.0.jar"]