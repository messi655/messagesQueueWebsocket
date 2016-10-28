# Quick start

Step by step:
1. Build websocket docker image: run `dockerbuild.sh` script
2. Go to `scripts` folder and excute `deploy_websocketclient.sh` script
3. Start docker container: `sudo docker run -d -v /apps/websocketclient:/usr/share/nginx/html -p 80:80 nginx/lz`


#Note:
Edit file websocket client (src/index.html) which match with socket server endpoint (`ws://localhost:8080/rabbitmqConsumer/receive`)