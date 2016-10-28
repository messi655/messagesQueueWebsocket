# Quick Start

1. Copy your SSL keystore to a directory of your choice
2. Encrypt your key with the password `123abc`
4. `sudo docker run -d -v /apps/producer:/srv/tomcat/webapps/ -v /apps/certs:/certs -v /apps/config:/apps/config -p 8443:8443 tomcat/https`

# Advanced 

- Edit `server.xml` to change location and password of your SSL private key
