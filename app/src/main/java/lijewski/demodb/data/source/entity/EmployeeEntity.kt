package lijewski.demodb.data.source.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "employees_table")
@Parcelize
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id") var id: Int = 0,
    @ColumnInfo(name ="first_name") val firstName: String,
    @ColumnInfo(name ="last_name") val lastName: String,
    @ColumnInfo(name ="birthdate") val birthdate: Long,
    @ColumnInfo(name ="gender") val gender: Int,
    @ColumnInfo(name ="adresses") val addressList: List<AddressEntity>
) : Parcelable

//TODO: add created, modified, and usernames who performed such operations fields