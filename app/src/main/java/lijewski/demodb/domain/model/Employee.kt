package lijewski.demodb.domain.model

import java.time.LocalDate

data class Employee(
    var firstName: String = "",
    var lastName: String = "",
    var birthdate: LocalDate = LocalDate.now(),
    var gender: Gender = Gender.NONE,
    var addressList: List<Address> = emptyList()
) {
    companion object {
        const val MAX_AGE: Long = 18
    }
}
//TODO: add created, modified dates, and usernames who performed such operations fields
