## ACHTUNG:
Wir nutzen das Compose-File-Format 2.1, damit der Jenkins-Slave erst nach Master hochgefahren wird.
Deswegen ist Docker 1.13 und Docker-Compose 1.10 notwendig. 

## Start Jenkins
    docker-compose build
    docker-compose up

## Connect to running container
    docker-compose exec jenkins bash

## Open Jenkins-Webpage
    http://localhost:8282

## Extract Plugins from running Jenkins
    curl -sSL "http://localhost:8282/pluginManager/api/xml?depth=1&xpath=/*/*/shortName|/*/*/version&wrapper=plugins" | perl -pe 's/.*?<shortName>([\w-]+).*?<version>([^<]+)()(<\/\w+>)+/\1 \2\n/g'|sed 's/ /:/' >jenkins/plugins.txt

## Copy Jenkins files from Docker to the host

1. Find container name
    ```
    docker ps --format "{{.Names}}" | grep jenkins
    > jenkinsdockerpipeline_jenkins_1
    ```

2.  Copy files
    ```
    docker cp jenkinsdockerpipeline_jenkins_1:/var/jenkins_home/ ./copy_jenkins_home
    ```
