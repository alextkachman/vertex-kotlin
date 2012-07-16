package org.vertx.kotlin.core

import org.vertx.java.core.Handler;

public fun <T> handler(handlerFun: (T)->Unit) : Handler<T?>  = object: Handler<T?> {
    public override fun handle(arg: T?) {
        handlerFun(arg!!)
    }
}

public fun <T> handler(handlerFun: T.()->Unit) : Handler<T?>  = object: Handler<T?> {
    public override fun handle(arg: T?) {
        arg!!.handlerFun()
    }
}

public fun handler(handlerFun: ()->Unit) : Handler<Void?>  = object: Handler<Void?> {
    public override fun handle(_: Void?) {
        handlerFun()
    }
}
