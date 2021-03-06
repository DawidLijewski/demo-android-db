package lijewski.demodb.databinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import lijewski.demodb.app.R
import lijewski.demodb.domain.model.Gender
import java.time.LocalDate
import java.time.Period

@BindingAdapter("visibleGone")
fun visibleGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleInvisible")
fun visibleInvisible(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("genderDrawable")
fun ImageView.genderDrawable(gender: Gender) {
    val resourceId = when (gender) {
        Gender.NONE -> R.drawable.ic_gender_none
        Gender.MALE -> R.drawable.ic_gender_male
        Gender.FEMALE -> R.drawable.ic_gender_female
        Gender.OTHER -> R.drawable.ic_gender_other
    }
    setImageResource(resourceId)
}

@BindingAdapter("genderText")
fun TextView.genderText(gender: Gender) {
    val resourceId = when (gender) {
        Gender.NONE -> R.string.gender_none
        Gender.MALE -> R.string.gender_male
        Gender.FEMALE -> R.string.gender_female
        Gender.OTHER -> R.string.gender_other
    }
    setText(resourceId)
}

@BindingAdapter("calculateAge")
fun TextView.calculateAge(localDate: LocalDate?) {
    val currentDate = LocalDate.now()
    text = if ((localDate != null) && (currentDate != null)) {
        Period.between(localDate, currentDate).years.toString()
    } else {
        "--"
    }
}
