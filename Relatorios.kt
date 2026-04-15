package hotel

fun relatoriosOperacionais() {
    println("\n================================")
    println("    Relatórios Operacionais       ")
    println("================================")

    val quartos = EstadoGlobal.quartosOcupados
    val quartosOcupados = (1..20).count { quartos[it] }
    val taxaOcupacao = quartosOcupados / 20.0 * 100

    val totalHospedes = EstadoGlobal.hospedes.size
    val totalReservas = EstadoGlobal.reservas.size
    val totalEventos  = EstadoGlobal.totalEventosConfirmados

    val recHospedagem = EstadoGlobal.receitaHospedagem
    val recEventos    = EstadoGlobal.receitaEventos
    val recTotal      = recHospedagem + recEventos

    println("\n${"─".repeat(42)}")
    println("%-28s %s".format("Indicador", "Valor"))
    println("─".repeat(42))
    println("%-28s %10d".format("Reservas confirmadas", totalReservas))
    println("%-28s %9.1f%%".format("Taxa de ocupação", taxaOcupacao))
    println("%-28s %10d".format("Quartos ocupados", quartosOcupados))
    println("%-28s %10d".format("Hóspedes cadastrados", totalHospedes))
    println("%-28s %10d".format("Eventos confirmados", totalEventos))
    println("─".repeat(42))
    println("%-28s %10s".format("Receita hospedagem", formatarMoeda(recHospedagem)))
    println("%-28s %10s".format("Receita eventos", formatarMoeda(recEventos)))
    println("%-28s %10s".format("RECEITA TOTAL", formatarMoeda(recTotal)))
    println("─".repeat(42))

    if (EstadoGlobal.reservas.isNotEmpty()) {
        println("\nDetalhe das reservas:")
        println("%-4s %-20s %-6s %-10s %10s".format("Res.", "Hóspede", "Quarto", "Tipo", "Total"))
        println("─".repeat(56))
        EstadoGlobal.reservas.forEachIndexed { i, res ->
            println("%-4d %-20s %-6d %-10s %10s".format(
                i + 1,
                res["hospede"],
                res["quarto"],
                res["tipo"],
                formatarMoeda(res["total"] as Double)
            ))
        }
    }
}
