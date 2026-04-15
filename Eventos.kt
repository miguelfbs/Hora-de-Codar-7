package hotel

import java.util.Locale.getDefault
import kotlin.math.ceil   // arredondamento para cima
import kotlin.math.floor  // arredondamento para baixo

fun eventos() {
    println("\n============")
    println("  Eventos  ")
    println("============")

    val convidados = readInt("Número de convidados: ")

    if (convidados !in 0..350) {
        println("Número de convidados inválido.")
        return
    }

    val (auditorio, cadeirasExtras) = selecionarAuditorio(convidados)
    if (auditorio == null) {
        println("Capacidade excedida. Nenhum auditório disponível.")
        return
    }

    if (cadeirasExtras > 0) {
        println("Auditório selecionado: $auditorio ($cadeirasExtras cadeiras adicionais)")
    } else {
        println("Auditório selecionado: $auditorio")
    }

    print("Dia da semana (ex: segunda, sabado): ")
    val dia = readln().trim().lowercase(getDefault())

    val horaInicio = readInt("Hora inicial (número inteiro): ")
    val duracao = readInt("Duração do evento (horas, 1-12): ")

    if (duracao !in 1..12) {
        println("Duração inválida. Informe entre 1 e 12 horas.")
        return
    }

    val (disponivel, mensagem) = verificarDisponibilidade(dia, horaInicio, duracao)
    println(mensagem)
    if (!disponivel) return

    print("Nome da empresa: ")
    val empresa = readln().trim()
    val horaFim = horaInicio + duracao

    println("Auditório reservado para $empresa: $dia às ${horaInicio}hs até ${horaFim}hs")

    val garconBase = ceil(convidados.toDouble() / 12).toInt()
    val garconReforco = floor(duracao.toDouble() / 2).toInt()
    val totalGarcons = garconBase + garconReforco
    val custoGarcons = totalGarcons * duracao * 10.50

    println("\nGarçons necessários: $totalGarcons")
    println("Custo com garçons  : ${formatarMoeda(custoGarcons)}")

    val cafe    = convidados * 0.2               // litros
    val agua    = convidados * 0.5               // litros
    val salgados = convidados * 7                // unidades

    val custoCafe    = cafe * 0.80
    val custoAgua    = agua * 0.40
    val custoSalgados = (salgados / 100.0) * 34.00   // /100 porque é por cento
    val custoBuffet  = custoCafe + custoAgua + custoSalgados

    println("\nBuffet:")
    println("  Café    : ${"%.1f".format(cafe)} L")
    println("  Água    : ${"%.1f".format(agua)} L")
    println("  Salgados: $salgados unidades")
    println("  Custo   : ${formatarMoeda(custoBuffet)}")

    val totalEvento = custoGarcons + custoBuffet

    println("\n─── INFORMAÇÕES DO EVENTO ─────────────────────")
    println("Auditório : $auditorio")
    println("Empresa   : $empresa")
    println("Dia       : $dia  |  Início: ${horaInicio}hs  |  Fim: ${horaFim}hs")
    println("Convidados: $convidados  |  Garçons: $totalGarcons  |  Duração: ${duracao}h")
    println("Garçons   : ${formatarMoeda(custoGarcons)}")
    println("Buffet    : ${formatarMoeda(custoBuffet)}")
    println("TOTAL     : ${formatarMoeda(totalEvento)}")
    println("─────────────────────────────────────────────")

    print("Confirmar reserva? (S/N): ")
    val confirma = readln().trim().uppercase(getDefault())

    if (confirma == "S") {
        EstadoGlobal.totalEventosConfirmados++
        EstadoGlobal.receitaEventos += totalEvento
        println("Reserva efetuada com sucesso.")
    } else {
        println("Reserva não efetuada.")
    }
}

fun selecionarAuditorio(convidados: Int): Pair<String?, Int> {
    return when {
        convidados <= 150 -> Pair("Laranja", 0)
        convidados <= 220 -> Pair("Laranja", convidados - 150)
        convidados <= 350 -> Pair("Colorado", 0)
        else              -> Pair(null, 0)
    }
}



fun verificarDisponibilidade(dia: String, horaInicio: Int, duracao: Int): Pair<Boolean, String> {
    val (abertura, fechamento) = when (dia) {
        "segunda", "terca", "quarta", "quinta", "sexta" -> Pair(7, 23)
        "sabado", "domingo" -> Pair(7, 15)
        else -> return Pair(false, "Dia da semana inválido.")
    }

    val horaFim = horaInicio + duracao

    return when {
        horaInicio < abertura  -> Pair(false, "Horário fora da janela (abertura: ${abertura}hs).")
        horaFim > fechamento   -> Pair(false, "Evento ultrapassa o fechamento (${fechamento}hs).")
        else                   -> Pair(true,  "Auditório disponível.")
    }
}
