package org.vertx.kotlin.core

import org.vertx.java.core.http.HttpServer
import org.vertx.java.core.http.HttpServerRequest
import org.vertx.java.core.http.ServerWebSocket
import org.vertx.java.core.http.RouteMatcher
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx

public fun HttpServer.requestHandler(handlerFun: HttpServerRequest.()->Unit): HttpServer
        = this.requestHandler(handler(handlerFun))!!

public fun HttpServer.websocketHandler(handlerFun: (ServerWebSocket)->Unit): HttpServer
        = this.websocketHandler(handler(handlerFun))!!

public fun HttpServer.routeMatcher(config: RouteMatcher.()->Unit): HttpServer
        = requestHandler(RouteMatcher(config) as Handler<HttpServerRequest?>)!!

fun Vertx?.createHttpServer(config: HttpServer.()->Unit) : HttpServer {
    val httpServer = this!!.createHttpServer()!!
    httpServer.config()
    return httpServer
}
