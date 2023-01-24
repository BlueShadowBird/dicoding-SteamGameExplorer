package id.web.dedekurniawan.steamgameexplorer.core.utils

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat


fun String.toHTML(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
} else {
    @Suppress("DEPRECATION")
    Html.fromHtml(this)
}

fun numberFormat(number: Float): String = DecimalFormat("#,###,###.##").format(number)

fun alert(view: View, message: String){
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}