package org.vertx.kotlin.core

import org.vertx.java.core.streams.ReadStream
import org.vertx.java.core.buffer.Buffer

public fun ReadStream.dataHandler(handler: (Buffer)->Any?) {
    this.dataHandler(handler(handler))
}

public fun ReadStream.exceptionHandler(handler: (Exception)->Any?) {
    this.exceptionHandler(handler(handler))
}

public fun ReadStream.endHandler(handler: ()->Any?) {
    this.endHandler(handler(handler))
}
