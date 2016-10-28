#!/bin/bash
cd ..
mvn clean install
sudo mkdir -p /apps/consumer
sudo chmod 777 /apps/*
rm -rf /apps/consumer/*
cp target/*.war /apps/consumer
