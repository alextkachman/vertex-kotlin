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

package org.vertx.kotlin.freemarker

import freemarker.template.DefaultObjectWrapper
import freemarker.template.TemplateModel
import org.vertx.java.core.json.JsonObject
import org.vertx.java.core.json.JsonArray
import freemarker.template.Template
import org.vertx.java.core.http.HttpServerRequest
import java.io.StringWriter

import org.vertx.kotlin.core.*

public class JsonWrapper() : DefaultObjectWrapper() {
    public override fun wrap(obj: Any?): TemplateModel? =
        super<DefaultObjectWrapper>.wrap(when (obj) {
            is JsonObject -> (obj as JsonObject).toMap()

            is JsonArray -> (obj as JsonArray).toList()
            else -> obj
        })
}

fun HttpServerRequest.end(template: Template, model: Any?) {
    val writer = StringWriter()
    template.process(model, writer)
    this.end(writer.toString() as String)
}
