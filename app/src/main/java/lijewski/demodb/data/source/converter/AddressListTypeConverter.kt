package lijewski.demodb.data.source.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lijewski.demodb.data.source.entity.AddressEntity
import java.lang.reflect.Type
import java.util.*

class AddressListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToAddressEntityList(data: String?): List<AddressEntity?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<AddressEntity?>?>() {}.type
        return gson.fromJson<List<AddressEntity?>>(data, listType)
    }

    @TypeConverter
    fun addressEntityListToString(someObjects: List<AddressEntity?>?): String? {
        return gson.toJson(someObjects)
    }
}
