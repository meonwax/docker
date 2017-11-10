# Prosody XMPP docker container

## Set docker user id
    export DOCKER_ID_USER="username"

## Set domain name
    export DOMAIN="example.com"

## Create certificate
    openssl req -nodes -newkey rsa:4096 -keyout resources/etc/prosody/certs/${DOMAIN}.key -out resources/etc/prosody/certs/${DOMAIN}.csr -subj "/C=DE/ST=Frankfurt/L=Frankfurt/O=IT Security/OU=IT Department/CN=${DOMAIN}"
    openssl x509 -req -days 3650 -in resources/etc/prosody/certs/${DOMAIN}.csr -signkey resources/etc/prosody/certs/${DOMAIN}.key -out resources/etc/prosody/certs/${DOMAIN}.crt
    chmod 644 resources/etc/prosody/certs/${DOMAIN}.key

## Edit configuration
   vim resources/etc/prosody/prosody.cfg.lua

## Build image
    docker build -t $DOCKER_ID_USER/prosody .

## Run container 
    docker run -d -p 5222:5222 -v prosody:/var/lib/prosody $DOCKER_ID_USER/prosody

## Create XMPP user
    docker exec -i -t <containername> /bin/sh
    prosodyctl register <username> <domainname> <secretpassword>

## Publish to docker hub (optional)
    docker login
    docker push $DOCKER_ID_USER/prosody
