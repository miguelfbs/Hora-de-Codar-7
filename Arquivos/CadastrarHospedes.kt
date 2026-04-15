package hotel

import java.time.LocalDateTime          // biblioteca padrão de data/hora do Java
import java.time.format.DateTimeFormatter
import java.util.Locale.getDefault

private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

fun cadastrarHospedes() {
    val hospedes = EstadoGlobal.hospedes

    while (true) {
        println("\n===========================")
        println("   Cadastro de Hóspedes    ")
        println("===========================")
        println(" 1. Cadastrar              ")
        println(" 2. Pesquisar exato        ")
        println(" 3. Pesquisar por prefixo  ")
        println(" 4. Listar (A-Z)           ")
        println(" 5. Atualizar cadastro     ")
        println(" 6. Remover cadastro       ")
        println(" 7. Voltar                 ")
        println("===========================")
        print("Opção: ")

        val opcao = readln().toIntOrNull()

        when (opcao) {
            1 -> opCadastrar(hospedes)
            2 -> opPesquisarExato(hospedes)
            3 -> opPesquisarPrefixo(hospedes)
            4 -> opListar(hospedes)
            5 -> opAtualizar(hospedes)
            6 -> opRemover(hospedes)
            7 -> return   // "return" sai da função; o menu principal recebe o controle
            else -> println("Opção inválida. Informe um número entre 1 e 7.")
        }
    }
}


private fun opCadastrar(hospedes: MutableList<EstadoGlobal.Hospede>) {
    if (hospedes.size >= 15) {
        println("Máximo de cadastros atingido.")
        return
    }

    print("Nome do hóspede: ")
    val nome = readln().trim()

    if (nome.isBlank()) {  // isBlank() = true se vazio ou só espaços
        println("Nome não pode estar em branco.")
        return
    }

    val jaExiste = hospedes.any { it.nome.equals(nome, ignoreCase = true) }
    if (jaExiste) {
        println("Hóspede já cadastrado.")
        return
    }

    val agora = LocalDateTime.now().format(formatter)

    hospedes.add(EstadoGlobal.Hospede(nome = nome, dataCadastro = agora))
    println("Operação realizada com sucesso. Hóspede \"$nome\" cadastrado em $agora.")
}

private fun opPesquisarExato(hospedes: List<EstadoGlobal.Hospede>) {
    print("Nome para pesquisa exata: ")
    val busca = readln().trim()

    val encontrado = hospedes.find { it.nome.equals(busca, ignoreCase = true) }

    if (encontrado != null) {
        println("Hóspede \"${encontrado.nome}\" foi encontrado (cadastrado em ${encontrado.dataCadastro}).")
    } else {
        println("Hóspede não encontrado.")
    }
}

private fun opPesquisarPrefixo(hospedes: List<EstadoGlobal.Hospede>) {
    print("Prefixo: ")
    val prefixo = readln().trim()

    // filter{} retorna uma NOVA lista com todos os itens que satisfazem a condição
    // startsWith() verifica se a string começa com o prefixo informado
    val resultados = hospedes.filter {
        it.nome.startsWith(prefixo, ignoreCase = true)
    }

    if (resultados.isEmpty()) {
        println("Nenhum hóspede encontrado com o prefixo \"$prefixo\".")
    } else {
        println("Resultados:")
        resultados.forEachIndexed { i, h -> println("  [${i + 1}] ${h.nome}") }
    }
}

private fun opListar(hospedes: List<EstadoGlobal.Hospede>) {
    if (hospedes.isEmpty()) {
        println("Nenhum hóspede cadastrado.")
        return
    }

    val ordenados = hospedes.sortedBy { it.nome.lowercase(getDefault()) }

    println("\n%-4s %-30s %-18s".format("Idx", "Nome", "Cadastro"))
    println("─".repeat(54))
    ordenados.forEachIndexed { i, h ->
        println("%-4d %-30s %-18s".format(i + 1, h.nome, h.dataCadastro))
    }
}

private fun opAtualizar(hospedes: MutableList<EstadoGlobal.Hospede>) {
    if (hospedes.isEmpty()) {
        println("Nenhum hóspede para atualizar.")
        return
    }

    opListar(hospedes)

    val idx = readInt("Índice para atualizar: ") - 1  // -1 porque a lista interna começa em 0

    if (idx !in hospedes.indices) {
        println("Índice inválido.")
        return
    }

    print("Novo nome: ")
    val novoNome = readLine()!!.trim()

    if (novoNome.isBlank()) {
        println("Nome não pode estar em branco.")
        return
    }

    val jaExiste = hospedes.any { it.nome.equals(novoNome, ignoreCase = true) }
    if (jaExiste) {
        println("Já existe um hóspede com esse nome.")
        return
    }

    val agora = LocalDateTime.now().format(formatter)
    hospedes[idx] = hospedes[idx].copy(nome = novoNome, dataCadastro = agora)
    println("Operação realizada com sucesso.")
}

private fun opRemover(hospedes: MutableList<EstadoGlobal.Hospede>) {
    if (hospedes.isEmpty()) {
        println("Nenhum hóspede para remover.")
        return
    }

    opListar(hospedes)

    val idx = readInt("Índice para remover: ") - 1

    if (idx !in hospedes.indices) {
        println("Índice inválido.")
        return
    }

    val removido = hospedes.removeAt(idx)
    println("Operação realizada com sucesso. \"${removido.nome}\" removido.")
}
