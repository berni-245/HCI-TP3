import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

interface LocaleEnum {
    val resourceId: Int

    @Composable
    fun getString(): String {
        return stringResource(resourceId)
    }
}
