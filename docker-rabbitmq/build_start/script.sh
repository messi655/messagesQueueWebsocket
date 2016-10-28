#!/bin/bash
cd ../ & sudo docker build -t rabbitmq/lz .
sudo docker run -d -p 5672:5672 -p 15672:15672 rabbitmq/lz
