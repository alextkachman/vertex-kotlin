package org.vertx.kotlin.core

import org.vertx.java.core.net.NetServer
import org.vertx.java.core.net.NetSocket

public fun NetServer?.connectHandler(handler: (NetSocket)->Unit): NetServer {
    this!!.connectHandler(handler(handler))
    return this
}