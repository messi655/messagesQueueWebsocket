
######### Diagram of application

    users -> (https) producer api -> RabbiMQ -> consumer api -> websocket -> (http) users


Description:

    - Producer will be hosting on Tomcat 7.0.72 and expose port 8443 with https protocol

    - RabbitMQ is message queue server, this expose port 5672 and 15672

    - Consumer will be hosting on Tomcat 7.0.72 and expose port 8080 with http protocol

    - websocket will hosting on nginx and expose port 80 with https protocol


########### System information

- OS: Ubuntu 16.04
- Docker:
    - Docker compose: docker-compose version 1.8.0
    - Docker engine: Docker version 1.11.2
- Maven: Maven compiler plugin 3.2
- JDK: 1.7


####### Step by step to build and run message queue api
Automation:

    - Step 1: Run mainscript.sh
    - Step 2: Run script `AIO.sh`
    - Step 3: Run command: `sudo docker-compose up`

Manually:

Step 1. Run mainscript.sh , this script will create some folders in /apps

Step 2. Build docker images for each service

    1. Producer API:
        a. Build producer docker images: Go to `rabbitmqProducer/docker` folder and execute `dockerbuild.sh` script
        b. Build producer api uses maven: Go to `rabbitmqProducer/scripts` folder and execute `maven_build_rabbitmqProducer.sh` script

    2. RabbitMQ (message queue):
        a. Build rabbitmq docker image: Go to `docker-rabbitmq` folder and execute `dockerbuild.sh` script

    3. Consumer API:
        a. Build consumer docker images: Go to `rabbitmqConsumer/docker` folder and execute `dockerbuild.sh` script
        b. Build consumer api uses maven: Go to `rabbitmqConsumer/scripts` folder and execute `maven_build_rabbitmqConsumer.sh` script

    4. Web Socket:
        a. Build websocket docker images: Go to `websocketclient/docker` folder and execute `dockerbuild.sh` script
        b. Copy source code to map volume  uses maven: Go to `websocketclient/scripts` folder and execute `deploy_websocketclient.sh` script

Step 3. Start docker container

    - To run docker container for these servers we use docker compose to control this.
    - Start docker container by docker compose: `sudo docker-compose up`
    - Check docker container started: `sudo docker ps -a` 


############# Testing

- Access rabbitmq management: http://your_docker_host:15672/ (EX: http://localhost:15672/)
- Client UI: http://your_docker_host/ (EX: http://localhost/)
- Producer link: https://your_docker_host:8443/rabbitmqProducer/send
- Consumer endpoint (websocket server endpoint): http://your_docker_host:8080/rabbitmqConsumer/receive
- Send text to rabbitmq via producer api and get message via consumer api and show on UI by use websocket:

   - curl -k --data "text=xxxx" https://your_docker_host:8443/rabbitmqProducer/send
   - Open browse and enter url: http://your_docker_host

#Note: You can access to each endpoint api to get information:
    - Producer: https://your_docker_host:8443/rabbitmqProducer/
    - Consumer: http://your_docker_host:8080/rabbitmqConsumer/
      
