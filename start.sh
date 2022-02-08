#!/bin/bash

echo "###############  Downloading dataset and starting processing. [This step may take a while, please do not close this window] ##################"
python -m script

echo "###################### Bulding jar file ######################"
mvn package

echo "###################### Building application docker image ######################"
docker build -t springbootapplication .

echo "###################### Runnning docker compose ######################"
docker-compose up

$SHELL