package org.vertx.kotlin.examples.echo

import org.vertx.java.deploy.Verticle
import org.vertx.java.core.streams.Pump

import org.vertx.kotlin.core.*

public class EchoSslServer() : Verticle() {
    public override fun start() {
        vertx.createNetServer {
            setSSL(true)
            setKeyStorePath("./server-keystore.jks")
            setKeyStorePassword("wibble")

            connectHandler{ socket ->
                Pump.createPump(socket, socket)!!.start();
            }
        }
        .listen(1234);
    }
}