package org.vertx.kotlin.core

import org.vertx.java.core.net.NetSocket
import org.vertx.java.core.buffer.Buffer

public fun NetSocket.dataHandler(handler: (Buffer)->Unit) : NetSocket {
    dataHandler(handler(handler))
    return this
}

public fun NetSocket.endHandler(handler: ()->Unit) : NetSocket {
    endHandler(handler(handler))
    return this
}

public fun NetSocket.drainHandler(handler: ()->Unit) : NetSocket {
    drainHandler(handler(handler))
    return this
}
