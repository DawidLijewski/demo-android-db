package lijewski.demodb.domain.model

import java.util.*

data class Employee(
    var id: Int,
    var firstName: String = "",
    var lastName: String,
    var birthdate: Date, //TODO: change to Date
    var gender: Gender, //TODO: change to enum
    var addressList: List<Address>
)
//TODO: add created, modified, and usernames who performed such operations fields
