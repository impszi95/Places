# Places
> Shows information about business entries

## Table of contents
* [Build](#build)
* [Use](#use)
* [Technologies](#technologies)
* [Contact](#contact)

## Build
* Clone repository
* Make sure you have JDK 11, Maven 2.x, MongoDb
* Build with, mvn clean package
* Run with java -jar target/places-0.0.1-SNAPSHOT.jar

## Use
REST: <br>
**Create a new place**<br>
* POST localhost:8081/api/places/create <br><br>
Body obj:<br>
{name, address, openingHours[]}<br>
address: country,state,city,street,streetNum<br>
openingHours[]:day, intervals[]<br>
intervals[]: from, to<br>

**Sample query in CreatePlace.txt**<br>

**Get all places**<br>
* GET localhost:8081/api/places <br><br>

**Get a place by placeId**<br>
* GET localhost:8081/api/places/{placeId} <br><br>
    
## Technologies
* Java 11, Spring Boot
* MongoDb

## Contact
Created by Bence Varga - impszi95@gmail.com <br />
