package org.vertx.kotlin.core

import org.vertx.java.core.Vertx
import org.vertx.java.core.http.HttpServer
import org.vertx.java.core.net.NetServer
import org.vertx.java.core.net.NetClient

fun Vertx?.createHttpServer(config: HttpServer.()->Unit) : HttpServer {
    val httpServer = this!!.createHttpServer()!!
    httpServer.config()
    return httpServer
}

fun Vertx?.createNetServer(config: NetServer.()->Unit) : NetServer {
    val netServer = this!!.createNetServer()!!
    netServer.config()
    return netServer
}

fun Vertx?.createNetClient(config: NetClient.()->Unit) : NetClient {
    val netClient = this!!.createNetClient()!!
    netClient.config()
    return netClient
}

public fun Vertx?.setPeriodic(l: Long, longHandler: (Long)->Unit) : Long
        = this!!.setPeriodic(l, handler(longHandler));

