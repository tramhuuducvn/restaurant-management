FROM openjdk:17
ADD build/libs/restaurant-management-0.0.1-SNAPSHOT.jar restaurant-management-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "restaurant-management-0.0.1-SNAPSHOT.jar"]

# docker build -t restaurant_app .
# cfdd7d2134bfe9693d08b315c9969521c87871dc52254825a096c1600ed289fa
# docker run --network restaurant-management-net --name restaurant-container -p 8080:8080 -d restaurant_app
# backend-container: 426d34477dcf4d5ec18a8ee4f8491e5ea4948dc2ee833d3e01d070b9a7a45b37

# docker network create restaurant-management-net

# docker run --name mysqldb --network restaurant-management-net
# -e MYSQL_ROOT_PASSWORD=12345678
# -e MYSQL_DATABASE=restaurant_db
# -e MYSQL_USER=tramhuuducvn
# -e MYSQL_PASSWORD=12345678
# -p 7070:3306
# -d mysql:5.7

# docker run --name mysqldb --network restaurant-management-net -e MYSQL_ROOT_PASSWORD=12345678 -e MYSQL_DATABASE=restaurant_db -e MYSQL_USER=tramhuuducvn -e MYSQL_PASSWORD=12345678 -p 7070:3306 -d mysql:5.7

# mysqldb container: b2fc84cd4a289f005d07647591bca2056ff6c48c33fb34ff37916d783cf3ff1d