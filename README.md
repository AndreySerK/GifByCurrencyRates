GifByCurrencyService
====

Обращается к стороннему сервису курсов валют, и отдает gif в ответ:
- если курс по отношению к USD за сегодня стал выше вчерашнего, отдает рандомную отсюда https://giphy.com/search/rich 
- если ниже - отсюда https://giphy.com/search/broke 

REST API курсов валют - https://docs.openexchangerates.org/ 

REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide 

Сервис на Spring Boot 2 + Java.

Для взаимодействия с внешними сервисами используется Feign.

Для сборки используется Gradle.


===
Download and run:

git clone https://github.com/AndreySerK/GifByCurrencyRates

cd GifByCurrencyRates
gradle build  # To create jar file
java -jar */build/libs/Alfa-0.0.1-SNAPSHOT.jar # To run this service


Try to open: [http://localhost:8081/rates/rub](http://localhost:8081/rates/rub)

Docker
===

    docker build -t alfa .
    docker run -p 8081:8081 docker.io/library/alfa

Try to open: http://localhost:8081/rates/rub
