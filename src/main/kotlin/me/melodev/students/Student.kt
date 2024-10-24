package me.melodev.students

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.sql.ResultSet

@Serializable
class Student(
    val id: String,
    val name: String,
    val enrollingId: String,
    val email: String,
    @Transient val password: String = ""
) {
    companion object {
        fun mapToStudent(resultSet: ResultSet) = Student(
            resultSet.getString("id"),
            resultSet.getString("name"),
            resultSet.getString("enrollingId"),
            resultSet.getString("email"),
            resultSet.getString("password")
        )
    }
}


@Serializable
data class StudentCreateDto (
    val name: String,
    val enrollingId: String,
    val email: String,
    val password: String
)
