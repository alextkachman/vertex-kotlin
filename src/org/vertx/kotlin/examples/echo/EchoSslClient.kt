package org.vertx.kotlin.examples.echo

import org.vertx.java.deploy.Verticle
import org.vertx.java.core.streams.Pump

import org.vertx.kotlin.core.*
import org.vertx.java.core.buffer.Buffer

public class EchoSslClient() : Verticle() {
    public override fun start() {
        vertx.createNetClient {
            setSSL(true)
            setTrustAll(true)

            connect(1234){ socket ->
                socket.dataHandler{ buffer ->
                    System.out.println("Net client receiving:\n-------\n$buffer\n-------")
                }

                //Now send some data
                for (var i in 0..9) {
                    val str = "hello$i\n"
                    System.out.print("Net client sending: $str")
                    socket.write(str)
                }
            }
        }
    }
}