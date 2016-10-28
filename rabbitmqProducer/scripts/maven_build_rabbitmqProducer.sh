#!/bin/bash
cd .. 
mvn clean install
rm -rf /apps/producer/*
cp target/*.war /apps/producer
cp docker/keystore /apps/certs
