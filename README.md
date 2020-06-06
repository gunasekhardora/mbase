# mbase

## To build locally 
mvn assembly:assembly

## To run locally
java -jar target/mbase-jar-with-dependencies.jar

### To create a user locally
curl -X POST "localhost:1111/api/user" -d '{"id": "1","name":"user1","country":"IND"}' -H 'Content-Type: application/json'

### To list all users
curl "localhost:1111/api/users" | jq

### To list specific user
curl "localhost:1111/api/user/1" | jq

### To delete a user
curl -X DELETE "localhost:1111/api/user/1"
