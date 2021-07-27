# scalardb-sample-app

Please use cassandra as database.
The schema for database table is provided in file 'email1.json' .

The commands to run the application using gradle is provided below:
--------------------------------------------------------------------

1. Using transaction service
---------------------------
To add data : 
./gradlew run --args="-mode transaction -action add -name demo -email s1@demo.com -phone 1111100000"

To get data for a specifc name and email:
./gradlew run --args="-mode transaction -action get -name demo -email s1@demo.com"

To get all data for a specifc name:
./gradlew run --args="-mode transaction -action scan -name demo"


2. Using storage service
-------------------------
To add data : 
./gradlew run --args="-mode storage -action add -name demo -email s1@demo.com -phone 1111100000"

To get data for a specifc name and email:
./gradlew run --args="-mode storage -action get -name demo -email s1@demo.com"

To get all data for a specifc name:
./gradlew run --args="-mode storage -action scan -name demo"
