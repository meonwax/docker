# Prosody XMPP docker container

## Set docker user id
    export DOCKER_ID_USER="username"

## Build image
    docker build -t $DOCKER_ID_USER/prosody .

## Publish to docker hub (optional)
    docker login
    docker push $DOCKER_ID_USER/prosody

## Run container 
    docker run -d -p 5222:5222 -v ./data:/var/lib/prosody $DOCKER_ID_USER/prosody

## Create XMPP user
    docker exec -i -t <containername> /bin/sh
    prosodyctl register <username> <domainname> <secretpassword>
