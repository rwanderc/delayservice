# DelayService

A REST service that exposes endpoints that block the thread for a specific amount of time before returning.
 This is a very useful tool to help running tests of performance, cache, concurrency, etc.
 
# Running
* Build with Maven by running `mvn clean install`.
* And then execute the application with `java -jar target/delayservice-1.0-SNAPSHOT.jar`