package lijewski.demodb.data.source.mapper

import lijewski.demodb.data.source.entity.AddressEntity
import lijewski.demodb.data.source.entity.EmployeeEntity
import lijewski.demodb.domain.model.Address
import lijewski.demodb.domain.model.Employee
import javax.inject.Inject

class EmployeeMapper @Inject constructor(private val addressMapper: AddressMapper) : Mapper<EmployeeEntity, Employee> {
    override fun mapFromEntity(type: EmployeeEntity): Employee {
        return Employee(
            id = type.id,
            firstName = type.firstName,
            lastName = type.lastName,
            birthdate = type.birthdate,
            gender = type.gender,
            addressList = convertAddressEntityList(type.addressList)
        )
    }

    override fun mapToEntity(type: Employee): EmployeeEntity {
        return EmployeeEntity(
            id = type.id,
            firstName = type.firstName,
            lastName = type.lastName,
            birthdate = type.birthdate,
            gender = type.gender,
            addressList = convertAddressList(type.addressList)
        )
    }

    private fun convertAddressEntityList(addressEntityList: List<AddressEntity>): List<Address> {
        return addressEntityList.map {
            addressMapper.mapFromEntity(it)
        }
    }

    private fun convertAddressList(addressList: List<Address>): List<AddressEntity> {
        return addressList.map {
            addressMapper.mapToEntity(it)
        }
    }
}
