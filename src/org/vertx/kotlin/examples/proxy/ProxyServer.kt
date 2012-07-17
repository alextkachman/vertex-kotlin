package org.vertx.kotlin.examples.proxy

import org.vertx.java.deploy.Verticle

import org.vertx.kotlin.core.*

public class ProxyServer() : Verticle() {
    public override fun start() {
        val client = vertx.createHttpClient {
            setHost("localhost")
            setPort(8282)
        }

        vertx.createHttpServer {
            requestHandler {
                val req = this

                println("Proxying request: $uri")
                val forwardReq = client.request(method!!, uri!!) { resp ->
                    println("Proxying response: ${resp.statusCode}");
                    req.statusCode = resp.statusCode
                    req.responseHeaders.putAll(resp.headers());
                    req.setChunked(true);

                    resp.dataHandler { data ->
                        println("Proxying response body:" + data)
                        req.write(data)
                    }
                    resp.endHandler {
                        req.end()
                    }
                }

                forwardReq.headers()!!.putAll(req.headers())
                req.dataHandler { data ->
                    println("Proxying response body:" + data)
                    forwardReq.write(data)
                }
                req.endHandler {
                    forwardReq.end()
                }
            }
        }
        .listen(8080)
    }
}