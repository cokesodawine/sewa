# SEWA

🚨🚨 Dev is cooking...DIY (Docker It Yourself!!)

## Vehicle Service
```bash
$ docker build -t vehicle-service ./vehicle-service
$ docker run -d --name vehicle-service-cont -p 8080:8080 vehicle-service
$ docker start vehicle-service-cont
```
test it.. it should give Hello World :)
```bash
curl http://localhost:8080/vehicle-service/
```
## Vehicle Database

