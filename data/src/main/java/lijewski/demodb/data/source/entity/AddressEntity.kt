package lijewski.demodb.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int,
    @SerializedName("street") val street: String,
    @SerializedName("city") val city: String,
    @SerializedName("postal_code") val postalCode: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("extra_desc") val extraDesc: String
) : Parcelable
