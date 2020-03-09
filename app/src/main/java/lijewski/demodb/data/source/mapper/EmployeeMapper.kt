package lijewski.demodb.data.source.mapper

import lijewski.demodb.data.source.entity.EmployeeEntity
import lijewski.demodb.domain.model.Employee
import lijewski.demodb.domain.model.Gender
import java.time.LocalDate
import javax.inject.Inject

class EmployeeMapper @Inject constructor(private val addressMapper: AddressMapper) :
    Mapper<EmployeeEntity, Employee> {
    override fun mapFromEntity(type: EmployeeEntity): Employee {
        return Employee(
            firstName = type.firstName,
            lastName = type.lastName,
            birthdate = LocalDate.ofEpochDay(type.birthdate),
            gender = Gender.values()[type.gender],
            addressList = addressMapper.mapFromEntityList(type.addressList)
        )
    }

    override fun mapToEntity(type: Employee): EmployeeEntity {
        return EmployeeEntity(
            firstName = type.firstName,
            lastName = type.lastName,
            birthdate = type.birthdate.toEpochDay(),
            gender = type.gender.ordinal,
            addressList = addressMapper.mapToEntityList(type.addressList)
        )
    }
}
