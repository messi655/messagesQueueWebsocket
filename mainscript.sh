#!/bin/bash

echo "create folder /apps/certs"
sudo mkdir -p /apps/certs

echo "create folder /apps/config"
sudo mkdir -p /apps/config

echo "create folder /apps/producer"
sudo mkdir -p /apps/producer

echo "create folder /apps/consumer"
sudo mkdir -p /apps/consumer

echo "create folder /apps/websocketclient"
sudo mkdir -p /apps/websocketclient

echo "set permission of /apps"
sudo chmod 777 /apps/* 

