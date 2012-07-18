package org.vertx.kotlin.examples

import org.vertx.java.core.Vertx
import org.vertx.java.core.http.HttpServer
import org.vertx.kotlin.core.*
import org.vertx.kotlin.examples.koolio.createKoolioServer

fun main(args: Array<String>) {
    val port = 8080
    val vertx = Vertx.newVertx()!!
    val server = if (args.size > 0 && args[0] == "koolio") {
        createKoolioServer(vertx)
    } else {
        createSimpleServer(vertx)
    }
    server.listen(port)!!

    println("""
Running vertx kotlin server on:
http://localhost:$port/

Press ENTER to terminate:""")
    readLine()
    server.close()
}

fun createSimpleServer(vertx: Vertx): HttpServer {
    val BUFF_SIZE = 32 * 1024
    return vertx.createHttpServer{
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
}