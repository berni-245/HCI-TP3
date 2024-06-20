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
import hci_tp3.smart_penguin.repository.RoutineRepository
import hci_tp3.smart_penguin.ui.devices.DevicesViewModel
import hci_tp3.smart_penguin.ui.devices.ac.AcViewModel
import hci_tp3.smart_penguin.ui.devices.blind.BlindViewModel
import hci_tp3.smart_penguin.ui.devices.lamp.LampViewModel
import hci_tp3.smart_penguin.ui.devices.vacuum.VacuumViewModel
import hci_tp3.smart_penguin.ui.rooms.RoomsViewModel
import hci_tp3.smart_penguin.ui.routines.RoutinesViewModel

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as ApiApplication)
    val roomRepository = application.roomRepository
    val deviceRepository = application.deviceRepository
    val routineRepository = application.routineRepository
    return ViewModelFactory(
        roomRepository,
        deviceRepository,
        routineRepository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}

class ViewModelFactory (
    private val roomRepository: RoomRepository,
    private val deviceRepository: DeviceRepository,
    private val routineRepository: RoutineRepository,
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

            isAssignableFrom(BlindViewModel::class.java) ->
                BlindViewModel(deviceRepository)

            isAssignableFrom(AcViewModel::class.java) ->
                AcViewModel(deviceRepository)

            isAssignableFrom(VacuumViewModel::class.java) ->
                VacuumViewModel(deviceRepository, roomRepository)

            isAssignableFrom(RoutinesViewModel::class.java) ->
                RoutinesViewModel(routineRepository)

            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}