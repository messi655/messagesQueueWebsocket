# Quick Start


Step by step:
1. Build consumer docker image: run `dockerbuild.sh` script
2. Go to `scripts` folder and excute `maven_build_rabbitmqConsumer.sh`
3. Start docker container: `sudo docker run -d -v /apps/consumer:/srv/tomcat/webapps/ -v /apps/config:/apps/config -p 8080:8080 tomcat/http-lz`

# Advanced 
If you want to change `server.xml`, please do it before run `dockerbuild.sh`
