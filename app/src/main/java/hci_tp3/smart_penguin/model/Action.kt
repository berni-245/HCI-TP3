package hci_tp3.smart_penguin.model

class Action (
    var device : DeviceForRoutine,
    var actionName : String,
    var params : List<String>,
    var meta : Any? = null
){
}