package me.melodev.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.resources.*
import me.melodev.students.registerStudentRoutes

fun Application.configureRouting() {
    install(DoubleReceive)
    install(Resources)

    // Registering routes
    registerStudentRoutes()
}

