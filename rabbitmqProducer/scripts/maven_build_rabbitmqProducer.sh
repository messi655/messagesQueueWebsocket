#!/bin/bash
cd .. 
mvn clean install
sudo mkdir -p /apps/producer
sudo rm -rf /apps/producer/*
sudo chmod 777 -R /apps/*
cp target/*.war /apps/producer
cp docker/keystore /apps/certs
