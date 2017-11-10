# Prosody XMPP docker container

## Set docker user id
    export DOCKER_ID_USER="username"

## Create certificate
    openssl req -nodes -newkey rsa:4096 -keyout resources/etc/prosody/certs/default.key -out resources/etc/prosody/certs/default.csr -subj "/C=GB/ST=London/L=London/O=Global Security/OU=IT Department/CN=domain.name"
    openssl x509 -req -days 365 -in resources/etc/prosody/certs/default.csr -signkey resources/etc/prosody/certs/default.key -out resources/etc/prosody/certs/default.crt

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
