package org.mbte.vertx

import org.vertx.java.core.Vertx

public fun Vertx.setPeriodic(l: Long, longHandler: (Long)->Unit) : Long
        = this.setPeriodic(l, handler(longHandler));
