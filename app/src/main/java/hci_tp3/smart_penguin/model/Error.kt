package hci_tp3.smart_penguin.model

data class Error(
    val code: Int?,
    val message: String,
    val description: List<String>? = null
)