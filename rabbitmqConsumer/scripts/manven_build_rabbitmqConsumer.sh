#!/bin/bash
cd ..
mvn clean install
sudo rm -rf /apps/consumer/*
cp target/*.war /apps/consumer
