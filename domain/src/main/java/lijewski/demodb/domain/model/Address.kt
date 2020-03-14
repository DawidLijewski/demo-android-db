package lijewski.demodb.domain.model

data class Address(
    val id: Int,
    val street: String,
    val city: String,
    val postalCode: String,
    val region: String,
    val country: String,
    val extraDesc: String
)
