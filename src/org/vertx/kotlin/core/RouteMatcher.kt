package org.vertx.kotlin.core

import org.vertx.java.core.http.RouteMatcher
import org.vertx.java.core.http.HttpServerRequest
import org.vertx.java.core.http.HttpServer

public fun RouteMatcher(config: RouteMatcher.()->Unit) : RouteMatcher {
    val matcher = RouteMatcher()
    matcher.config()
    return matcher
}

public fun RouteMatcher.get(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = get(pattern, handler(handler))
public fun RouteMatcher.put(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = put(pattern, handler(handler))
public fun RouteMatcher.post(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = post(pattern, handler(handler))
public fun RouteMatcher.delete(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = delete(pattern, handler(handler))
public fun RouteMatcher.options(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = options(pattern, handler(handler))
public fun RouteMatcher.head(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = head(pattern, handler(handler))
public fun RouteMatcher.trace(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = trace(pattern, handler(handler))
public fun RouteMatcher.connect(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = connect(pattern, handler(handler))
public fun RouteMatcher.patch(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = patch(pattern, handler(handler))
public fun RouteMatcher.all(pattern: String, handler: HttpServerRequest.()->Unit): Unit
        = all(pattern, handler(handler))
public fun RouteMatcher.getWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = getWithRegEx(regex, handler(handler))
public fun RouteMatcher.putWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = putWithRegEx(regex, handler(handler))
public fun RouteMatcher.postWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = postWithRegEx(regex, handler(handler))
public fun RouteMatcher.deleteWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = deleteWithRegEx(regex, handler(handler))
public fun RouteMatcher.optionsWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = optionsWithRegEx(regex, handler(handler))
public fun RouteMatcher.headWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = headWithRegEx(regex, handler(handler))
public fun RouteMatcher.traceWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = traceWithRegEx(regex, handler(handler))
public fun RouteMatcher.connectWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = connectWithRegEx(regex, handler(handler))
public fun RouteMatcher.patchWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = patchWithRegEx(regex, handler(handler))
public fun RouteMatcher.allWithRegEx(regex: String, handler: HttpServerRequest.()->Unit): Unit
        = allWithRegEx(regex, handler(handler))
public fun RouteMatcher.noMatch(handler: HttpServerRequest.()->Unit): Unit
        = noMatch(handler(handler))
