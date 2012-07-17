package org.vertx.kotlin.core

import org.vertx.java.core.streams.WriteStream
import org.vertx.java.core.buffer.Buffer

public fun WriteStream.drainHandler(handler: ()->Any?) {
    this.drainHandler(handler(handler))
}

public fun WriteStream.exceptionHandler(handler: (Exception)->Any?) {
    this.exceptionHandler(handler(handler))
}
