package hotel
import java.util.Locale.getDefault

fun reservasDeQuartos() {
    val nome = EstadoGlobal.nomeUsuario
    println("\n==============")
    println("   Reservas   ")
    println("==============")

    val diaria = readDouble("Informe o valor da diária: ")

    if (diaria <= 0) {
        println("Valor inválido, $nome.")
        return
    }

    val qtdDiarias = readInt("Informe a quantidade de diárias (1-30): ")

    if (qtdDiarias !in 1..30) {
        println("Valor inválido, $nome.")
        return
    }

    print("Informe o nome do hóspede: ")
    val nomeHospede = readln().trim()

    val (fator, tipoNome) = escolherTipoQuarto() ?: return

    val numQuarto = escolherQuarto() ?: return

    val subtotal = diaria * qtdDiarias * fator
    val taxa = subtotal * 0.10
    val total = subtotal + taxa

    println("\nResumo:")
    println("Hóspede : $nomeHospede")
    println("Quarto  : $numQuarto ($tipoNome)")
    println("Subtotal: ${formatarMoeda(subtotal)}")
    println("Taxa 10%: ${formatarMoeda(taxa)}")
    println("Total   : ${formatarMoeda(total)}")

    print("\n$nome, confirma a reserva? (S/N): ")
    val confirma = readln().trim().uppercase(getDefault())

    if (confirma == "S") {
        EstadoGlobal.quartosOcupados[numQuarto] = true

        EstadoGlobal.reservas.add(
            mapOf(
                "hospede" to nomeHospede,
                "quarto" to numQuarto,
                "tipo" to tipoNome,
                "diarias" to qtdDiarias,
                "total" to total
            )
        )

        EstadoGlobal.receitaHospedagem += total

        println("Reserva efetuada com sucesso.")
        exibirMapaQuartos()
    } else {
        println("Reserva não efetuada.")
    }
}

fun escolherTipoQuarto(): Pair<Double, String>? {
    print("Tipo de quarto (S=Standard / E=Executivo / L=Luxo): ")
    return when (readln().trim().uppercase(getDefault())) {
        "S" -> Pair(1.00, "Standard")
        "E" -> Pair(1.35, "Executivo")
        "L" -> Pair(1.65, "Luxo")
        else -> {
            println("Tipo inválido. Voltando ao menu.")
            null
        }
    }
}

fun escolherQuarto(): Int? {
    while (true) {
        val num = readInt("Escolha um quarto (1-20): ")

        if (num !in 1..20) {
            println("Número de quarto inválido. Informe entre 1 e 20.")
            continue
        }

        if (EstadoGlobal.quartosOcupados[num]) {
            println("Quarto $num já está ocupado.")
            val livres = (1..20).filter { !EstadoGlobal.quartosOcupados[it] }
            if (livres.isEmpty()) {
                println("Não há quartos disponíveis no momento.")
                return null
            }
            println("Quartos livres: $livres")
        } else {
            return num
        }
    }
}

fun exibirMapaQuartos() {
    println("\nMapa de quartos  (L=Livre  O=Ocupado)")
    println("┌────┬────┬────┬────┬────┐")

    // "chunked(4)" divide a lista em sublistas de 4 elementos
    val linhas = (1..20).chunked(4)

    for (linha in linhas) {
        // Número do quarto
        val nums = linha.joinToString(" │ ", "│ ", " │") { "%2d".format(it) }
        // Status L ou O
        val status = linha.joinToString(" │ ", "│ ", " │") {
            if (EstadoGlobal.quartosOcupados[it]) " O" else " L"
        }
        println(nums)
        println(status)
        println("├────┼────┼────┼────┼────┤")
    }
}


fun readDouble(prompt: String): Double {
    print(prompt)
    return readln().trim().toDoubleOrNull() ?: 0.0
}

fun readInt(prompt: String): Int {
    print(prompt)
    return readln().trim().toIntOrNull() ?: 0
}

fun formatarMoeda(valor: Double): String {
    return "R$ %,.2f".format(valor)
        .replace(",", "X")
        .replace(".", ",")
        .replace("X", ".")
}
