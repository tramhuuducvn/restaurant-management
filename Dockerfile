# FROM openjdk:17
# VOLUME /tmp
# ARG JAR_FILE
# COPY build/libs/*.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:17
ADD build/libs/restaurant-management-0.0.1-SNAPSHOT.jar restaurant-management.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "restaurant-management.jar"]

# => NETWORK
# docker network create restaurant-net
# id: 9d9bc4668d4f7ab7ee9c981fb1f752043b83999056d51b26ebc3f1b2da9a0d1d

# => SPRING BOOT APP IMAGE
# docker build -t restaurant-app .
# docker run --network restaurant-net -p 8080:8080 restaurant-app

# => SPRING BOOT APP CONTAINER
# docker container run --network restaurant-net  --name restaurant-management-container -p 8080:8080 -d restaurant-app
# id: 205cb55637413129bed54ec11720f9c53faf99ebbf98e8e865eb12ccb80dbaf6 

# => MYSQL CONTAINER
# docker run --name mysqldb --network restaurant-net -e MYSQL_ROOT_PASSWORD=12345678 -e MYSQL_DATABASE=restaurant_db -e MYSQL_USER=tramhuuducvn -e MYSQL_PASSWORD=12345678 -p 7070:3306 -d mysql:5.7
# container id: 18354a0f7a86b583b0db639043e40159e8b89a07d75463b9076653d64c8768ca

# How to connect with mysqldb?
# - docker exec -it <container_id> bash
# - mysql -u<username> -p<password>
# - show databases;

# GRANT ALL PRIVILEGES 
# Login by root
# show database;
# use database_name
# GRANT ALL PRIVILEGES ON *.* TO 'user_name'@'%';