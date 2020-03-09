@file:JvmName("BindingUtils")

package lijewski.demodb.databinding

import androidx.databinding.InverseMethod
import lijewski.demodb.domain.model.Gender


@InverseMethod("positionToGender")
fun genderToPosition(gender: Gender?): Int {
    return gender?.ordinal ?: 0
}

fun positionToGender(position: Int): Gender {
    return Gender.values()[position]
}
