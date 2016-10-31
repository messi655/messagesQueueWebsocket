#!/bin/bash

root_path=$(pwd)
echo "Build rabbitmq image"
cd $root_path/docker-rabbitmq/
sh dockerbuild.sh

echo "Build rabbitmq consumer"
cd $root_path/rabbitmqConsumer/docker/
sh dockerbuild.sh

echo "Maven build rabbitmq consumer"
cd  $root_path/rabbitmqConsumer/scripts/
sh maven_build_rabbitmqConsumer.sh

echo "Build rabbitmq Producer"
cd $root_path/rabbitmqProducer/docker/
sh dockerbuild.sh

echo "Maven build rabbitmq Producer"
cd  $root_path/rabbitmqProducer/scripts/
sh maven_build_rabbitmqProducer.sh

echo "Build websocker image"
cd $root_path/websocketclient/docker/
sh dockerbuild.sh

echo "Deploy websocket client"
cd $root_path/websocketclient/scripts/
sh deploy_websocketclient.sh

