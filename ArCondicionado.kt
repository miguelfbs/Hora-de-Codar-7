package hotel


import java.util.Locale.getDefault

fun arCondicionado() {
    val nome = EstadoGlobal.nomeUsuario
    println("\n=================")
    println(" Ar Condicionado ")
    println("=================")

    val orcamentos = mutableListOf<Pair<String, Double>>()

    var continuar = true

    while (continuar) {
        print("Nome da empresa: ")
        val empresa = readln().trim()

        val valorPorAparelho = readDouble("Valor por aparelho (R$): ")
        val quantidade       = readInt("Quantidade de aparelhos: ")
        val percDesconto     = readDouble("Percentual de desconto (%): ")
        val minimoDesconto   = readInt("Mínimo de aparelhos para desconto: ")
        val deslocamento     = readDouble("Valor fixo de deslocamento (R$): ")

        val bruto = valorPorAparelho * quantidade

        val desconto = if (quantidade >= minimoDesconto) bruto * (percDesconto / 100.0) else 0.0

        val total = bruto - desconto + deslocamento

        println("O serviço de $empresa custará ${formatarMoeda(total)}")

        orcamentos.add(Pair(empresa, total))

        print("Deseja informar novos dados, $nome? (S/N): ")
        continuar = readln().trim().uppercase(getDefault()) == "S"
    }

    if (orcamentos.size < 2) {
        println("São necessários ao menos dois orçamentos para comparação.")
        return
    }

    val melhor = orcamentos.minBy { it.second }
    val pior   = orcamentos.maxBy { it.second }

    val difPerc = ((pior.second - melhor.second) / pior.second) * 100

    println("\n─── Comparativo de Orçamentos ────────────────")
    println("Melhor: ${melhor.first} — ${formatarMoeda(melhor.second)}")
    println("Pior  : ${pior.first} — ${formatarMoeda(pior.second)}")
    println("Diferença: ${"%.1f".format(difPerc)}% entre a melhor e a pior proposta")
    println("──────────────────────────────────────────────")
    println("O orçamento de menor valor é o de ${melhor.first} por ${formatarMoeda(melhor.second)}")
}
