# issue-tracker

On projects main folder, you can execute the following commands :

mvn clean package -DskipTests

then

docker-compose up

To test the app you can use the following url for swagger ui or you can use postman :

http://localhost:8080/swagger-ui/index.html#

Sample json's : 

add developer :

{"name":"yoda"}

add story : (I add two date to define sprint interval, sprintStartDate and sprintEndDate)

{
  "issueId": "1",
  "title": "story title 1",
  "description": "story description 1",
  "storyPoint": 4,
  "status": "NEW",
  "fromDate": "2021-12-13T09:00",
  "toDate": "2021-12-20T09:00",
  "sprintStartDate": "2021-12-13T08:00",
  "sprintEndDate": "2021-12-20T10:00"
}

assign story to developer :

{
  "storyId":1,
  "developerId":1
}

add bug:
{
  "issueId": "1",
  "title": "bug title 1",
  "description": "bug description 1",
  "status": "NEW",
  "priority": "CRITICAL"
}

assign bug to developer :
{
  "bugId":1,
  "developerId":1
}


