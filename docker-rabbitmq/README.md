#https://github.com/frodenas/docker-rabbitmq

1. Build Rabbitmq docker image:
	- Run script: ./dockerbuild.sh

2. Manual start docker container:
	- sudo docker run -d -p 15672:15672 -p 5672:5672 rabbitmq/lz
