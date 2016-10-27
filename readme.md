## Extract Plugins from running Jenkins

    curl -sSL "http://localhost:8080/pluginManager/api/xml?depth=1&xpath=/*/*/shortName|/*/*/version&wrapper=plugins" | perl -pe 's/.*?<shortName>([\w-]+).*?<version>([^<]+)()(<\/\w+>)+/\1 \2\n/g'|sed 's/ /:/'

## Start Jenkins
    cd jenkins
    docker build .
    docker run -it -p 8080:8080  -v /var/run/docker.sock:/var/run/docker.sock --group-add=daemon --name jenkins <image-id>

## Connect to running container
    docker exec -it jenkins bash
