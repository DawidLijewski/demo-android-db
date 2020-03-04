package lijewski.demodb.data.source.mapper

import lijewski.demodb.data.source.entity.EmployeeEntity
import lijewski.demodb.domain.model.Employee
import javax.inject.Inject

class EmployeeMapper @Inject constructor(private val addressMapper: AddressMapper) :
    Mapper<EmployeeEntity, Employee> {
    override fun mapFromEntity(type: EmployeeEntity): Employee {
        return Employee(
            id = type.id,
            firstName = type.firstName,
            lastName = type.lastName,
            birthdate = type.birthdate,
            gender = type.gender,
            addressList = addressMapper.mapFromEntityList(type.addressList)
        )
    }

    override fun mapToEntity(type: Employee): EmployeeEntity {
        return EmployeeEntity(
            id = type.id,
            firstName = type.firstName,
            lastName = type.lastName,
            birthdate = type.birthdate,
            gender = type.gender,
            addressList = addressMapper.mapToEntityList(type.addressList)
        )
    }
}
