package org.vertx.kotlin.core

import org.vertx.java.core.http.HttpClient
import org.vertx.java.core.http.HttpClientRequest
import org.vertx.java.core.http.HttpClientResponse

public fun HttpClient.request(method: String, uri: String, handler: (HttpClientResponse)->Unit) : HttpClientRequest
        = this.request(method, uri, handler(handler))!!