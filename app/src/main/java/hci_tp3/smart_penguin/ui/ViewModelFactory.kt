package hci_tp3.smart_penguin.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import hci_tp3.smart_penguin.ApiApplication
import hci_tp3.smart_penguin.repository.DeviceRepository
import hci_tp3.smart_penguin.repository.RoomRepository
import hci_tp3.smart_penguin.ui.devices.DevicesViewModel
import hci_tp3.smart_penguin.ui.devices.LampViewModel
import hci_tp3.smart_penguin.ui.rooms.RoomsViewModel

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as ApiApplication)
    val roomRepository = application.roomRepository
    val deviceRepository = application.deviceRepository
    return ViewModelFactory(
        roomRepository,
        deviceRepository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}

class ViewModelFactory (
    private val roomRepository: RoomRepository,
    private val deviceRepository: DeviceRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(RoomsViewModel::class.java) ->
                RoomsViewModel(roomRepository)

            isAssignableFrom(DevicesViewModel::class.java) ->
                DevicesViewModel(deviceRepository)

            isAssignableFrom(LampViewModel::class.java) ->
                LampViewModel(deviceRepository)

            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}