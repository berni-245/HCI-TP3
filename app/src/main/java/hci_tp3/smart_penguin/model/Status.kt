package hci_tp3.smart_penguin.model

import hci_tp3.smart_penguin.remote.model.RemoteStatus

enum class Status {
    ON, OFF;

    companion object {
        fun asRemoteModel(value: Status): String {
            return when (value) {
                ON -> RemoteStatus.ON
                else -> RemoteStatus.OFF
            }
        }
    }
}