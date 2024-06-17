package hci_tp3.smart_penguin

class DataSourceException(
    var code: Int,
    message: String,
    var details: List<String>?
) : Exception(message)