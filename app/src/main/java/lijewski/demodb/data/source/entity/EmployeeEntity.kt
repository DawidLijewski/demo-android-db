package lijewski.demodb.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "employees")
@Parcelize
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("birthdate") var birthdate: String, //TODO: change to Date
    @SerializedName("gender") var gender: String, //TODO: change to enum
    @SerializedName("adresses") var addressList: List<AddressEntity>
) : Parcelable

//TODO: add created, modified, and usernames who performed such operations fields