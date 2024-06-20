package hci_tp3.smart_penguin.model.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
interface LocaleEnum {
    val resourceId: Int
    @Composable
    fun getString(): String {
        return stringResource(resourceId)
    }
}

interface ApiEnum {
    val apiString: String
    @Composable
    fun getApiString(): String {
        return apiString
    }
}