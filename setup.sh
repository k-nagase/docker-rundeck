#/bin/bash

DATA_DIR="
./data/lib/mysql
./data/lib/rundeck/.ssh
./data/lib/rundeck/data
./data/lib/rundeck/logs
./data/lib/rundeck/var/storage
./data/rundeck/projects
"
mkdir -p ${DATA_DIR}

docker-compose up -d
