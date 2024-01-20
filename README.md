# Tasks API Guide

Minimum Required Java Version: 17


### MacOS, Linux, or Windows w/Git bash
- <code>./bin/run.sh</code>

### Windows without Git bash
- <code>./mvnw clean test package</code>
- <code>java -jar target/*.jar</code>

### Or Run in Docker
- <code>docker build . -t tasks-api</code>
- <code>docker -p 8080:8080 -it tasks-api</code>
  - Access API http://localhost:8080/tasks

## The following endpoints are currently available
- Get all tasks
  - GET "/tasks"
- Get task by id
  - GET "/tasks/:id"
- Create a task
  - POST "/tasks"
    - BODY: String title, String, description
- Update a task by id
  - PUT "/tasks/:id"
    - Optional Params: String title, String description, Boolean completed