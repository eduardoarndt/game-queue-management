# spring-webflux-example

Application made to learn reactive programming. See [spring-webflux-study](https://github.com/pachecoka/spring-webflux-study) for a documentation on the topic :)

## How to build and run
```
mvn install
mvn spring-boot:run
```

## API
```
POST /new/game/${gameName}

POST /${gameName}/players/add

POST /${gameName}/start

POST /${gameName}/finish

POST /${gameName}/turn/change

GET /${gameName}/players

GET /${gameName}/turn
```
