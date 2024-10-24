package me.melodev.students

import java.sql.Connection
import java.sql.Statement
import java.util.*

class StudentRepository(private val connection: Connection) {

    companion object {
        private const val CREATE_STUDENT = "CREATE TABLE IF NOT EXISTS students(id varchar UNIQUE, name varchar, enrollingId varchar UNIQUE, email varchar UNIQUE , password varchar)"
        private const val INSERT_STUDENT = "INSERT INTO students (id, name, enrollingId, email, password) VALUES (?,?,?,?,?)"
        private const val GET_STUDENT = "SELECT * FROM students WHERE id = ?"
    }

    init {
        // @TODO: Use migrations
        connection.createStatement().use { stmt -> stmt.execute(CREATE_STUDENT)}
    }

    fun getStudentById(id: String): Student? {
        val stmt = connection.prepareStatement(GET_STUDENT)

        stmt.setString(1, id)

        val result = stmt.executeQuery()

        if(!result.next())
            return null

        return Student.mapToStudent(result)
    }

    fun createStudent(student: Student): String? {
        val stmt = connection.prepareStatement(INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS)

        stmt.setString(1, UUID.randomUUID().toString())
        stmt.setString(2, student.name)
        stmt.setString(3, student.enrollingId)
        stmt.setString(4, student.email)
        stmt.setString(5, student.password)

        stmt.execute()

        val result = stmt.generatedKeys

        if(!result.next())
            return null

        return result.getString(1)
    }
}