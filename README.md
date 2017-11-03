# A docker container for REST API hosted on Heroku cloud

## Technical description
This maven project demonstrates,
- A Java Jersey REST API service
- Runs over embedded Jetty Server
- Imaged in a Docker container
- The docker container is deployed on Heroku cloud

## What it does?
The web service maintains employee information in an organisation. The employee information can be manipulated (Add, delete, fetch) by REST APIs.

## What it covers?
- Maven project structure
  - Handling build dependencies, plugins, packaging..
  - Use of external libraries in maven projects, like logger, String utilities..
- Embedding Jetty server in application.
- REST API resources
	- HTTP request handling
	- Data Transfer Object (DTO)
	- Converting Java POJO to/from XML and JSON 
- Dockerizing an application
- Deploying docker container on Heroku cloud


## Dockerizing the application
docker build,
``` 
docker build --tag=dockerrestapiservice .
```

docker run,

``` 
docker run -p 8889:8080 -t -i dockerrestapiservice
``` 

## Deployment

Login to heroku
```
heroku login
```
Then login to container registry
```
heroku container:login
```
Now create heroku application
```
heroku create
```
Heroku assigns a random generated name to your application, like salty-fortress-1234. You can rename that name (I explain how to rename your heroku app below)

Now it is time to push your docker container to heroku
```
heroku container:push web
```
### Rename your heroku application
- Go to your heroku dashboard, https://dashboard.heroku.com
- Select your newly created application and go to Setting tab
- Rename your application, you will get a warning that renaming application on web page does not automatically change the reference in your local working copy. you have to rename it manually in your local working copy,
```
git remote rm heroku
heroku git:remote -a new_name_of_your_application
```

### Access REST API
The rest service docker container is deployed on Heroku and can be accessed by,
https://employee-rest-docker.herokuapp.com/employees/all

For application Heroku deployment (without docker container) look at my other repository (https://github.com/azam-akram/employee_rest_web_services)

## API methods
Following API methods are supported,
- Get list of all employees
> GET http://{serverurl:port}/employees/all

- GET information of an employee whose id is sent in HTTP request
> GET http://{serverurl:port}/employees/{id}
``` json
{
    "orgName": "My Organisation",
    "employees": [
        {
            "id": 2,
            "name": "Employee 3",
            "department": "Research"
        },
        {
            "id": 1,
            "name": "Employee 4",
            "department": "Testing"
        }
	]
}
```
- Add one or more employee record by sending xml/json in POST request body.
> POST http://{serverurl:port}/employees/add

Client sends employee data to be added in xml or json structure and set HTTP header Content-type = application/xml or application/json
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<organization>
	<orgName>My Organisation</orgName>
    <employees>
        <employee>
            <name>Employee 1</name>
            <department>Research</department>
        </employee>
        <employee>
            <name>Employee 2</name>
            <department>Testing</department>
        </employee>
    </employees>
</organization>
```
- Delete a record by employee id
> DELETE http://{serverurl:port}/employees/{id}
# docker-rest-api-service
