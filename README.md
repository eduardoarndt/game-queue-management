# spring-webflux-example

Application made with learnings objectives. See [spring-webflux-study](https://github.com/pachecoka/spring-webflux-study).

## Build and run
```
mvn install
mvn spring-boot:run
```

## Endpoints
```
POST /new/game/${gameName}

POST /${gameName}/players/add

POST /${gameName}/start

POST /${gameName}/finish

POST /${gameName}/turn/change

GET /${gameName}/players // Flux!

GET /${gameName}/turn
```
