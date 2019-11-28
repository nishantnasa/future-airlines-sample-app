# README

This repository contains a sample java microservice generating some files with simulated random data. The app can be build locally in docker containers and run using docker-compose and Makefile tasks.

## Run locally
```
make build-local-base # create base build docker image
make build-local # create app docker image
make compose-local-java # run the app locally passing parameters using docker-compose
```

## Clean local
```
make destroy
make clean
```
