package org.mbte.vertx

import org.vertx.java.core.Handler;

public fun <T> handler(handlerFun: (T)->Unit) : Handler<T?>  = object: Handler<T?> {
    public override fun handle(request: T?) {
        handlerFun(request!!)
    }
}

public fun <T> handler(handlerFun: T.()->Unit) : Handler<T?>  = object: Handler<T?> {
    public override fun handle(request: T?) {
        request!!.handlerFun()
    }
}

public fun handler(handlerFun: ()->Unit) : Handler<Void?>  = object: Handler<Void?> {
    public override fun handle(_: Void?) {
        handlerFun()
    }
}
