package lijewski.demodb.domain.model

data class Employee(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthdate: String, //TODO: change to Date
    val gender: String, //TODO: change to enum
    val addressList: List<Address>
)
//TODO: add created, modified, and usernames who performed such operations fields
