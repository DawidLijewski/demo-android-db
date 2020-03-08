package lijewski.demodb.domain.model

import java.time.LocalDate

data class Employee(
    var id: Int,
    var firstName: String = "",
    var lastName: String,
    var birthdate: LocalDate,
    var gender: Gender, //TODO: change to enum
    var addressList: List<Address>
) {
    companion object {
        const val MAX_AGE: Long = 18
    }
}
//TODO: add created, modified dates, and usernames who performed such operations fields
