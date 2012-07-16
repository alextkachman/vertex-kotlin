package org.vertx.kotlin.core

import org.vertx.java.core.Vertx
import org.vertx.java.core.http.HttpServer

fun main(args: Array<String>) {
    val BUFF_SIZE = 32 * 1024
    val vertx = Vertx.newVertx()!!
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

    while(true) {
        Thread.sleep(100.toLong())
    }
}
