package org.vertx.kotlin.examples.koolio

import org.vertx.java.core.Vertx
import org.vertx.java.core.http.HttpServer
import org.vertx.kotlin.core.*

import kotlin.dom.*
import io.kool.template.html.*
import java.util.Date

/**
 * Lets create a sample server which uses a [Kool Template](http://kool.io/templates.html)
 */
fun createKoolioServer(vertx: Vertx): HttpServer {
    println("Creating a sample Kool Template server: http://kool.io/templates.html")
    val BUFF_SIZE = 32 * 1024
    return vertx.createHttpServer{
        setReceiveBufferSize(BUFF_SIZE)
        setSendBufferSize(BUFF_SIZE)
        setAcceptBacklog(32000)

        routeMatcher {
            var k = 0
            getWithRegEx(".*") {
                end(html {
                  head {
                      title("Koolio Template Sample")
                  }
                  body {
                    h1("This is a Kool Template")

                    p {
                        addText("This is request ")
                        b((++k).toString())
                        addText(" from a ")
                        a(href = "http://kool.io/templates.html", text = "Kool Template")
                        addText(" rendered using ")
                        a(href = "http://vertx.io", text = "vertx")
                        addText(" with the ")
                        a(href = "https://github.com/alextkachman/vertex-kotlin/blob/master/ReadMe.md", text = "kotlin language")
                    }

                    p {
                        addText("the time is now: ")
                        b(Date().toString())
                    }
                  }
                })
            }
        }
    }
}