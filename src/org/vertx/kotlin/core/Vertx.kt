package org.vertx.kotlin.core

import org.vertx.java.core.Vertx
import org.vertx.java.core.http.HttpServer
import org.vertx.java.core.http.HttpClient
import org.vertx.java.core.net.NetServer
import org.vertx.java.core.net.NetClient

public fun Vertx?.createHttpServer(config: HttpServer.()->Unit) : HttpServer {
    val httpServer = this!!.createHttpServer()!!
    httpServer.config()
    return httpServer
}

public fun Vertx?.createHttpClient(config: HttpClient.()->Unit) : HttpClient {
    val httpClient = this!!.createHttpClient()!!
    httpClient.config()
    return httpClient
}

public fun Vertx?.createNetServer(config: NetServer.()->Unit) : NetServer {
    val netServer = this!!.createNetServer()!!
    netServer.config()
    return netServer
}

public fun Vertx?.createNetClient(config: NetClient.()->Unit) : NetClient {
    val netClient = this!!.createNetClient()!!
    netClient.config()
    return netClient
}

public fun Vertx?.setPeriodic(l: Long, longHandler: (Long)->Unit) : Long
        = this!!.setPeriodic(l, handler(longHandler));

