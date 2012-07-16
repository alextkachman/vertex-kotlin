package org.vertx.kotlin.core

import org.vertx.java.core.net.NetClient
import org.vertx.java.core.net.NetSocket

public fun NetClient?.connect(port: Int,  connectCallback: (NetSocket)->Unit): NetClient
        = this!!.connect(port, handler(connectCallback))!!

public fun NetClient?.connect(port: Int,  host: String, connectCallback: (NetSocket)->Unit): NetClient
        = this!!.connect(port, host, handler(connectCallback))!!
