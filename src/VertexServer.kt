package org.vertx.kotlin.core

import org.vertx.java.core.Vertx
import org.vertx.java.core.http.HttpServer
import org.vertx.java.deploy.Verticle
import org.vertx.java.core.http.HttpServerRequest

public class ServerExample() : Verticle() {
    public override fun start() {
        val BUFF_SIZE = 32 * 1024
        vertx.createHttpServer{
            setReceiveBufferSize(BUFF_SIZE)
            setSendBufferSize(BUFF_SIZE)
            setAcceptBacklog(32000)

            routeMatcher {
                var k = 0
                getWithRegEx(".*") {
                    println(k++)
                    end("Hello, World!")
                }
            }
        }
        .listen(8080)
    }
}
