package org.vertx.kotlin.examples.echo

import org.vertx.java.deploy.Verticle
import org.vertx.java.core.streams.Pump

import org.vertx.kotlin.core.*

public class EchoServer() : Verticle() {
    public override fun start() {
        vertx.createNetServer {
            connectHandler{ socket ->
                Pump.createPump(socket, socket)!!.start();
            }
        }
        .listen(1234);
    }
}