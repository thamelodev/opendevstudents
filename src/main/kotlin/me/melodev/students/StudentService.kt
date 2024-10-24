package me.melodev.students

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.melodev.providers.CredentialEncoder
import me.melodev.providers.EmailValidator
import java.util.*

class StudentService(
    private val emailValidator: EmailValidator,
    private val credentialEncoder: CredentialEncoder,
    private val studentRepository: StudentRepository
) {

    suspend fun createStudent(dto: StudentCreateDto): String = withContext(Dispatchers.IO) {
        // Checks if email provided is valid
        if(emailValidator.isInvalid(dto.email))
            throw Throwable("Invalid email provided")

        val student = Student(
            UUID.randomUUID().toString(),
            dto.name,
            dto.enrollingId,
            dto.email,
            credentialEncoder.encode(dto.password),
        )

        studentRepository.createStudent(student)?.let {
            return@withContext it
        }

        // @TODO: Create specific exceptions
        throw Throwable("Failed to create student ${dto.name}")
    }

    suspend fun getStudentById(id: String) = withContext(Dispatchers.IO) {
        studentRepository.getStudentById(id)?.let { return@withContext it }

        // @TODO: Create specific exceptions
        throw Throwable("Student with id $id could not be found")
    }
}
