package hotel

object EstadoGlobal {

    var nomeUsuario: String = ""

    val quartosOcupados = BooleanArray(21) { false }

    val reservas = mutableListOf<Map<String, Any>>()

    data class Hospede(
        val nome: String,
        val dataCadastro: String
    )

    val hospedes = mutableListOf<Hospede>()

    var totalEventosConfirmados: Int = 0
    var receitaEventos: Double = 0.0

    var receitaHospedagem: Double = 0.0
}
