package org.vertx.kotlin.freemarker

import org.vertx.kotlin.core.*

import org.vertx.java.core.json.JsonObject
import org.vertx.java.deploy.Verticle
import freemarker.template.Configuration
import java.io.File
import java.io.StringWriter

public class FreeMarkerVerticle() : Verticle() {
    public override fun start() {
        val config = this.config

        val freeMarkerConfig = Configuration()
        freeMarkerConfig.setObjectWrapper(JsonWrapper())

        val dir = config.getString("directoryForTemplateLoading")
        if(dir != null) {
            freeMarkerConfig.setDirectoryForTemplateLoading(File(dir))
        }

        val name = config.getString("name")

        eventBus.registerLocalHandler<JsonObject>("freemarker.service.$name") {
            val writer = StringWriter()
            freeMarkerConfig.getTemplate(body!!.getString("template")!!)!!.process(body!!.getObject("model")!!, writer)
            reply(JsonObject().putString("rendered", writer.toString()))
        }
    }
}
