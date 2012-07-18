package org.vertx.kotlin.examples

import org.vertx.kotlin.core.createHttpServer
import org.vertx.java.core.Vertx
import org.vertx.kotlin.core.*

fun main(args: Array<String>) {
    val vertx = Vertx.newVertx()!!

    val BUFF_SIZE = 32 * 1024
    val port = 8080
    val server = vertx.createHttpServer{
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
    .listen(port)!!

    println("""
Running vertx kotlin server on:
http://localhost:$port/

Press ENTER to terminate:""")
    readLine()
    server.close()
}