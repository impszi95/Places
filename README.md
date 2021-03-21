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
**Place** <br>
**Create a new place**<br>
* POST localhost:8081/api/places/create <br>
Body obj:
{name, address, openingHours[]}

address: country,state,city,street,streetNum
openingHours[]:day, intervals[]
intervals[]: from, to

Sample query in CreatePlace.txt

**Get all places**<br>
* GET localhost:8081/api/places <br><br>

**Get a place by placeId**<br>
* GET localhost:8081/api/places/{placeId} <br><br>
    
## Technologies
* Java 11, Spring Boot
* MongoDb

## Contact
Created by Bence Varga - impszi95@gmail.com <br />
