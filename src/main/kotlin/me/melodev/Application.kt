package me.melodev

import io.ktor.server.application.*
import io.ktor.server.netty.*
import me.melodev.plugins.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureSecurity()
    configureHTTP()
    configureInjection()
    configureRouting()
    configureStatusPages()
}
