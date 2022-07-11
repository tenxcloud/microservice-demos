#!/bin/sh

docker build -t 192.168.90.214/system_containers/details:1.0 .
docker push 192.168.90.214/system_containers/details:1.0
