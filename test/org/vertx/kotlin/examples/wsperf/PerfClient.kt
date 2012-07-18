/*
 * Copyright 2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vertx.kotlin.examples.wsperf

import org.vertx.java.core.streams.Pump
import org.vertx.java.deploy.Verticle
import org.vertx.kotlin.core.*
import org.vertx.java.core.http.HttpClient
import java.util.LinkedList
import org.vertx.java.core.http.WebSocket
import java.util.HashSet
import org.vertx.java.core.buffer.Buffer

public class PerfClient() : Verticle() {
    val CONNS = 100
    val STR_LENGTH = 8 * 1024
    val STATS_BATCH = 1024 * 1024
    val BUFF_SIZE = 32 * 1024
    var statsCount = 0.toLong()
    var connectCount = 0
    val websockets = LinkedList<WebSocket>()
    val wss = HashSet<WebSocket>()

    var message = ({
            val sb = StringBuilder(STR_LENGTH)
            for (i in 0..STR_LENGTH) {
                sb.append('X')
            }
            sb.toString()!!
        })()

    public override fun start() {
        println("Starting perf client")
        createHttpClient(){
            setPort(8080)
            setHost("localhost")
            setMaxPoolSize(CONNS)

            setReceiveBufferSize(BUFF_SIZE)
            setSendBufferSize(BUFF_SIZE)
            setConnectTimeout(60000)
            setBossThreads(4)
        }.connect(0)
    }

    fun HttpClient.connect(count: Int) {
        var start = 0.toLong()
        connectWebsocket("/echo/websocket") {
            setWriteQueueMaxSize(BUFF_SIZE)
            dataHandler { data ->
                if (!wss.contains(this)) {
                    wss.add(this)
                }

                statsCount += data.length()
                val l = System.currentTimeMillis()
                if(start == 0.toLong()) {
                    start = l
                    statsCount = 0.toLong()
                }
                println("${(statsCount*1000.toDouble())/((1024*1024.toDouble())*(l-start))} mb/s")
            }

            websockets.add(this)

            connectCount++
            if (connectCount == CONNS) {
                startWebSocket()
            }
        }

        if (count + 1 < CONNS) {
            runOnLoop { connect(count + 1) }
        }
    }

    fun startWebSocket() {
        websockets.poll()!!.writeWebSocket()
        if (!websockets.isEmpty()) {
            runOnLoop { startWebSocket() }
        }
    }

    fun WebSocket.writeWebSocket() {
        if (!writeQueueFull()) {
            writeBinaryFrame(Buffer(message))
            runOnLoop { writeWebSocket() }
        } else {
            // Flow control
            drainHandler{ writeWebSocket() }
        }
    }
}
