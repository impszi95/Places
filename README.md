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
**Place** <br><br>
**Create a new place**<br>
* POST localhost:8081/api/places/create <br><br>

  "name" : "Vapiano",<br>
	"address" : {<br>
		"country" : "Hungary",<br>
		"state" : "Pest",<br>
		"city" : "Budapest",<br>
		"street" : "Vizimolnár",<br>
		"streetNum" : 14<br>
	},<br>
	"openingHours" : [<br>
		{<br>
			"day" : "Monday",<br>
			 "intervals": [<br>
                    {<br>
                        "from": "08:00",<br>
                        "to": "13:00"<br>
                    },<br>
                    {<br>
                        "from": "17:00",<br>
                        "to": "20:00"<br>
                    },<br>
                    ]<br>
		},<br>
    {<br>
      "day" : "Tuesday",<br>
      .<br>
      .<br>
      .<br>
      .<br><br>
**Get all places**<br>
* GET localhost:8081/api/places <br><br>

**Get a place by placeId**<br>
* GET localhost:8081/api/places/{placeId} <br><br>
    
## Technologies
* Java 11, Spring Boot
* MongoDb

## Contact
Created by Bence Varga - impszi95@gmail.com <br />
