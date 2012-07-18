## vert.x kotlin language support

This module provides a [kotlin](http://jetbrains.github.com/kotlin/) DSL to [vert.x](http://vertx.io/)

See the [introduction](http://jetbrains.github.com/kotlin/) to see how to use it.

### Run a sample kotlin vertx application

The following command should run a sample server (assuming you have [maven 3.x](http://maven.apache.org/) installed)

    mvn test-compile exec:java

You can now open http://localhost:8080/ and have fun :)

### Browse the API docs

Build the API docs locally via

    mvn site
    open target/site/apidocs/index.html

As you can see there are a number of extension methods on the [core Java API](http://vertx.io/api/java/api/) of [vert.x](http://vertx.io/)
