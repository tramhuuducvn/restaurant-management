FROM openjdk:17
ADD build/libs/restaurant-management-0.0.1-SNAPSHOT.jar restaurant-management-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "restaurant-management-0.0.1-SNAPSHOT.jar"]

# docker build -t restaurant_app .
# cfdd7d2134bfe9693d08b315c9969521c87871dc52254825a096c1600ed289fa
# docker run --network restaurant-management-net --name restaurant-container -p 8080:8080 -d restaurant_app
# backend-container: 14bb4836e9fb9a5c48905e4f754c446747776ff69e276ae119924db4520be27f

# docker network create restaurant-management-net

# docker run --name mysqldb --network restaurant-management-net
# -e MYSQL_ROOT_PASSWORD=12345678
# -e MYSQL_DATABASE=restaurant_app_db
# -e MYSQL_USER=tramhuuducvn
# -e MYSQL_PASSWORD=12345678
# -d mysql:5.7

# mysqldb container: 1b3546f2e8b6d54b8f86bca1d160bd1982a169ac39b88114cb34536243b997f8