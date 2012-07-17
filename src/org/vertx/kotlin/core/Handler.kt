package org.vertx.kotlin.core

import org.vertx.java.core.Handler;

public fun <T> handler(handlerFun: (T)->Any?) : Handler<T?>  = object: Handler<T?> {
    public override fun handle(arg: T?) {
        handlerFun(arg!!)
    }
}

public fun <T> handler(handlerFun: T.()->Any?) : Handler<T?>  = object: Handler<T?> {
    public override fun handle(arg: T?) {
        arg!!.handlerFun()
    }
}

public fun handler(handlerFun: ()->Any?) : Handler<Void?>  = object: Handler<Void?> {
    public override fun handle(_: Void?) {
        handlerFun()
    }
}
