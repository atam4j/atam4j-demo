#!/bin/bash
set -e;

mvn clean install

cd planes-4-sale-site
sudo docker build .

cd ../acceptance-tests-as-monitors/
sudo docker build .
