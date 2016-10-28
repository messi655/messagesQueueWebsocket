# Quick Start

Step by step:
1. Build producer docker image: run `dockerbuild.sh` script
2. Go to `scripts` folder and excute `maven_build_rabbitmqProducer.sh`
3. Start docker container: `sudo docker run -d -v /apps/producer:/srv/tomcat/webapps/ -v /apps/certs:/certs -v /apps/config:/apps/config -p 8443:8443 tomcat/https`

# Advanced 
If you want to change `server.xml`, please do it before run `dockerbuild.sh`

# Note:
    - keystore password: `123abc`
