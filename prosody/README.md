# Prosody XMPP docker container


## Build image

### Set docker user id
    export DOCKER_ID_USER="username"

### Set domain name
    export PROSODY_DOMAIN="example.com"

### Create certificate
    mkdir resources/etc/prosody/certs
    openssl req -nodes -newkey rsa:4096 -keyout resources/etc/prosody/certs/${PROSODY_DOMAIN}.key -out resources/etc/prosody/certs/${PROSODY_DOMAIN}.csr -subj "/C=DE/ST=Frankfurt/L=Frankfurt/O=IT Security/OU=IT Department/CN=${PROSODY_DOMAIN}"
    openssl x509 -req -days 3650 -in resources/etc/prosody/certs/${PROSODY_DOMAIN}.csr -signkey resources/etc/prosody/certs/${PROSODY_DOMAIN}.key -out resources/etc/prosody/certs/${PROSODY_DOMAIN}.crt
    chmod 644 resources/etc/prosody/certs/${PROSODY_DOMAIN}.key

### Edit configuration
    vim resources/etc/prosody/prosody.cfg.lua

### Build
    docker build -t ${DOCKER_ID_USER}/prosody .

### (optional) Publish to docker hub
    docker login
    docker push ${DOCKER_ID_USER}/prosody


## Run container
    docker run -d --name prosody -p 5222:5222 -v prosody:/var/lib/prosody ${DOCKER_ID_USER}/prosody


## User management

### Execute a shell inside running container
    docker exec -i -t prosody /bin/sh

### Create XMPP user
    prosodyctl register john example.com secretpassword

### Add users to roaster
    prosodyctl mod_roster_command subscribe_both john@example.com sarah@example.com
