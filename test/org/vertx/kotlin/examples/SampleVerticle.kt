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
package org.vertx.kotlin.examples

import org.vertx.kotlin.core.*
import org.vertx.java.core.Vertx
import org.vertx.java.core.http.HttpServer
import org.vertx.java.deploy.Verticle
import org.vertx.java.core.http.HttpServerRequest

public class SampleVerticle() : Verticle() {
    public override fun start() {
        val BUFF_SIZE = 32 * 1024
        vertx.createHttpServer{
            setReceiveBufferSize(BUFF_SIZE)
            setSendBufferSize(BUFF_SIZE)
            setAcceptBacklog(32000)

            routeMatcher {
                var k = 0
                getWithRegEx(".*") {
                    println(k++)
                    end("Hello, World!")
                }
            }
        }
        .listen(8080)
    }
}
