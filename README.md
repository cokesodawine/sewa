# SEWA

🚨🚨 It's still cooking...DIY (Dockerize It Yourself!!)


## Vehicle Service
```bash
$ docker build -t vehicle-service ./vehicle-service
$ docker run -d --name vehicle-service-cont -p 8080:8080 vehicle-service
$ docker start vehicle-service-cont
```
test it.. it should give Hello World!
```bash
$ curl http://localhost:8080/vehicle-service/
```

## Vehicle Database
```bash
$ docker build -t vehicle-serv-db ./vehicle-serv-db
$ docker run -p 1433:1433 --name vehicle-db-container -d vehicle-serv-db
$ docker exec -it vehicle-db-container /bin/bash
$ /usr/src/app/import-data.sh
```

## Establish Connection
```bash
$ docker network create sewa-micros-net
$ docker network connect sewa-micros-net vehicle-service-cont
$ docker network connect sewa-micros-net vehicle-serv-db
```
test it...
```bash
$ curl http://localhost:8080/vehicle-service/vehicles/instant
```
