Implemented features
- Implemented as Java Spring boot project with usage of database and Elasticsearch
- Usage of REST API to create and search incidents
- Persistence of incidents in database with usage of Hibernate
- Incidents contains attributes:
- - IncidentType
- - SeverityLevel
- - Timestamp
- - Location
- - id
- During persistence incidents are sync into Elasticsearch
- Incidents are searched in Elasticsearch with combination of attributes
- - IncidentTypes
- - SeverityLevels
- - Location, implemented as central location and distance from it
- - timestamps, implemented as interval from - to
- Usage of indexes in Elasticsearch on fields:
- - IncidentType
- - SeverityLevel
- - Timestamp
- - Location
- - id
- Unit testing for incident creation and searching
- Project contains Dockerfile to create image of application and docker-compose to run whole infrastructure

Guide to run
- Project uses Java 17. Use this version or newer
- Build project with usage of gradle via tool or commandline (.\gradlew build)
- create docker image via tool or commandline (docker build --tag emergency .). Expected name of image is emergency, this part is important for docker-compose
- run docker-compose file via tool or commandline (docker-compose up)

Please bear in mind that docker-compose use several application and each application requires usage of port/s:
- 8080 - Spring boot application
- 5432 - Postgres database
- 8888 - Pgadmin
- 9200 and 9300 - Elasticsearch
