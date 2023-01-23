package id.web.dedekurniawan.steamgameexplorer.core.utils

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.text.DecimalFormat


fun String.toHTML(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
} else {
    Html.fromHtml(this)
}

fun numberFormat(number: Float): String = DecimalFormat("#,###,###.##").format(number)