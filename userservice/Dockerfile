FROM java:8-jre
WORKDIR /usr/lib
ENV MYSQL_DATABASE=userdatabase
ENV MYSQL_URL=jdbc:mysql://localhost:3306/userdatabase
ENV MYSQL_USER=approot
ENV MYSQL_PASSWORD=root123
ADD ./target/userservice-1.0.jar /usr/lib/userservice-1.0.jar
ENTRYPOINT ["java","-jar","userservice-1.0.jar"]