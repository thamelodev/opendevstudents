package me.melodev.students

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.createStudentRoutes(service: StudentService) {
    post("/students") {
        val id = service.createStudent(call.receive())
        val response = mapOf("userId" to id, "creationTime" to System.currentTimeMillis().toString())
        call.respond(HttpStatusCode.Created, response)
    }
}

fun Route.findStudentByIdRoutes(service: StudentService) {
    get("/students/{id}") {
        val student = service.getStudentById(call.parameters["id"]!!)
        call.respond(HttpStatusCode.OK, student)
    }
}

fun Application.registerStudentRoutes() {
    val studentService by closestDI().instance<StudentService>()

    routing {
        createStudentRoutes(studentService)
        findStudentByIdRoutes(studentService)
    }
}