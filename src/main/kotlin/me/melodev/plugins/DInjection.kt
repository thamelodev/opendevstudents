package me.melodev.plugins

import io.ktor.server.application.*
import me.melodev.providers.BCryptEncoder
import me.melodev.providers.CredentialEncoder
import me.melodev.providers.EmailValidator
import me.melodev.providers.RFCEMailValidator
import me.melodev.students.StudentRepository
import me.melodev.students.StudentService
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.ktor.di
import java.sql.Connection

fun Application.configureInjection() {
    di {
        bindSingleton<Connection> { connectToPostgres(embedded = false) }
        bindProvider<EmailValidator> { RFCEMailValidator() }
        bindProvider<CredentialEncoder> { BCryptEncoder() }
        bindProvider<StudentRepository>{ StudentRepository(instance())}
        bindProvider<StudentService>{ StudentService(instance(), instance(), instance()) }
    }
}