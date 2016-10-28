1. Go to docker folder and run build script to build docker websocketclient images
	- cd docker
	- chmod 755 dockerbuild.sh
	- dockerbuild.sh
2. Edit file websocket client (src/index.html) which match with socket server endpoint
3. Go to scripts folder and execute the script in there (the script help to copy socket client to share volume)

==========================
We use docker-compose to run docker container service, so we don't need to run bellow command.
If you want to start docker container with docker engine(separate service) and without docker compose, run command: 

sudo docker run -d -v /apps/websocketclient:/usr/share/nginx/html -p 80:80 nginx/lz
