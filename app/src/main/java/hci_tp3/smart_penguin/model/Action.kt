package hci_tp3.smart_penguin.model

import hci_tp3.smart_penguin.remote.model.RemoteAction

class Action (
    var device : Device,
    var actionName : String,
    var params : List<String>,
    var meta : Any? = null
){
}