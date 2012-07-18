package org.vertx.kotlin.freemarker

import org.vertx.java.core.Vertx
import freemarker.template.Configuration
import freemarker.cache.CacheStorage
import java.util.concurrent.ConcurrentHashMap
import org.jboss.netty.channel.ChannelHandler.Sharable
import org.vertx.java.core.shareddata.Shareable

import org.vertx.kotlin.core.*
import org.vertx.java.deploy.Verticle
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.Lock
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition

/*
We do below complex manipulations with casts to Lock due potential class loader issues
*/
class FreemarkerConfiguration() : Configuration(), Shareable, Lock {
    val lock = ReentrantLock()

    public override fun lock() = lock.lock()

    public override fun tryLock(): Boolean = lock.tryLock()

    public override fun unlock()  = lock.unlock()

    public override fun tryLock(time: Long, unit: TimeUnit?): Boolean  = lock.tryLock(time, unit)

    public override fun lockInterruptibly() = lock.lockInterruptibly()

    public override fun newCondition(): Condition? = lock.newCondition()

   ;{
        setObjectWrapper(JsonWrapper())
    }
}

public fun Verticle.freemarkerConfig(name: String = "<default>", init: (Configuration.(Boolean)->Any?)? = null): Configuration
    = getVertx()!!.freemarkerConfig(name, init)

public fun Vertx.freemarkerConfig(name: String = "<default>", init: (Configuration.(Boolean)->Any?)? = null) : Configuration {
    val concurrentMap = sharedData.getMap<String, Configuration>(getClass()!!.getName())!!

    var config = concurrentMap.get(name)
    var first = false
    if (config == null) {
        val newConfig = FreemarkerConfiguration()
        newConfig.lock()

        val old = concurrentMap.putIfAbsent(name, newConfig)
        if (old != null) {
            newConfig.unlock()

            config = old
            (config as Lock).lock()
        }
        else {
            config = newConfig
            first = true
        }
    }
    else {
        (config as Lock).lock()
    }

    try {
        if (init != null) {
            config!!.init(first)
        }
    }
    finally {
        (config as Lock).unlock()
    }

    return config!!
}