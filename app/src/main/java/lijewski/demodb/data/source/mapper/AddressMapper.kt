package lijewski.demodb.data.source.mapper

import lijewski.demodb.data.source.entity.AddressEntity
import lijewski.demodb.domain.model.Address
import javax.inject.Inject

class AddressMapper @Inject constructor() : Mapper<AddressEntity, Address> {
    override fun mapFromEntity(type: AddressEntity): Address {
        return Address(
            id = type.id,
            street = type.street,
            city = type.city,
            postalCode = type.postalCode,
            region = type.region,
            country = type.country,
            extraDesc = type.extraDesc
        )
    }

    override fun mapToEntity(type: Address): AddressEntity {
        return AddressEntity(
            id = type.id,
            street = type.street,
            city = type.city,
            postalCode = type.postalCode,
            region = type.region,
            country = type.country,
            extraDesc = type.extraDesc
        )
    }
}
