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
package org.vertx.kotlin.examples.freemarker

import org.vertx.java.core.streams.Pump
import org.vertx.java.deploy.Verticle
import freemarker.template.Configuration
import freemarker.cache.FileTemplateLoader
import java.io.File
import java.util.HashMap
import java.util.Date
import java.io.StringWriter
import org.vertx.java.core.http.HttpServerRequest
import org.vertx.java.core.json.JsonObject
import freemarker.template.DefaultObjectWrapper
import freemarker.template.TemplateModel
import org.vertx.java.core.json.JsonArray

import org.vertx.kotlin.core.*
import org.vertx.kotlin.freemarker.*
import org.vertx.java.core.eventbus.Message
import org.vertx.java.core.Handler

public class ApplicationVerticle() : Verticle(), ConfigurableVerticle {}

public class FreeMarkerServer() : Verticle() {
    class object {
        val BUFF_SIZE = 32*1024
    }
    public override fun start() {
        createHttpServer {
            routeMatcher {
                noMatch {
                    val model = JsonObject().
                    putString("now", Date().toString())!!.
                    putString("message", "Hello, World!")!!.
                    putArray("list", JsonArray().addString("item 1")!!.addString("item 2")!!.addString("item 3"))

                    val messageHandler : Handler<Message<JsonObject?>?> =
                            handler<Message<JsonObject?>>{ reply ->
                                end(reply.body!!.getString("rendered") as String)
                            }

                    eventBus.send(
                            "freemarker.service.myFreemarker",
                            JsonObject().putString("template","index.ftl")!!.putObject("model", model),
                            messageHandler
                    )
                }
            }
        }.listen(8080, "localhost")
    }
}