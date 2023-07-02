echo
echo @CLEAN PROJECT
./gradlew clean
echo
echo  @ASSEMBLE PROJECT
./gradlew assemble 
docker build -t restaurant-app . 

echo
echo @CREATE NETWORK
docker network rm restaurant-net
docker network create restaurant-net

echo
echo @CREATE DATABASE CONTAINER
docker container stop mysqldb
docker container rm mysqldb
docker run --name mysqldb --network restaurant-net -e MYSQL_ROOT_PASSWORD=12345678 -e MYSQL_DATABASE=restaurant_db -e MYSQL_USER=tramhuuducvn -e MYSQL_PASSWORD=12345678 -p 7070:3306 -d mysql:5.7

echo
echo @WAITING FOR SET UP MYSQL....
sleep 150

echo
echo @CREATE SPRING APP CONTAINER
docker container stop restaurant-management-container
docker container rm restaurant-management-container
docker container run --network restaurant-net  --name restaurant-management-container -p 8080:8080 -d restaurant-app

echo @FINISHED SETUP

docker logs -f restaurant-management-container
docker start restaurant-management-container
docker logs -f restaurant-management-container

