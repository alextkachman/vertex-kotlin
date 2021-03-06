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

import org.vertx.java.deploy.Verticle
import org.vertx.java.core.streams.Pump
import org.vertx.java.core.http.ServerWebSocket

import org.vertx.kotlin.core.*

public class PerfServer() : Verticle() {
    class object {
        val BUFF_SIZE = 32*1024
    }

    public override fun start() {
        createHttpServer{
            receiveBufferSize = BUFF_SIZE
            sendBufferSize = BUFF_SIZE
            acceptBacklog = 32000

            websocketHandler { ws ->
                //System.out.println("connected " + ++count)
                Pump.createPump(ws, ws, BUFF_SIZE)!!.start()
            }
        }.listen(8080, "localhost")
    }
}
