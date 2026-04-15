package hotel

const val NOME_HOTEL = "Terabithia"
const val SENHA_CORRETA = "2678"

fun main() {
    autenticar()
}

fun autenticar() {
    println("==================================")
    println("  Bem-vindo ao Hotel $NOME_HOTEL  ")
    println("==================================")
    print("Nome de usuário: ")
    val nome = readln().trim()

    var tentativas = 0

    while (tentativas < 3) {
        print("Senha: ")
        val senha = readln().trim()

        if (senha == SENHA_CORRETA) {
            EstadoGlobal.nomeUsuario = nome
            println("\nBem-vindo ao Hotel $NOME_HOTEL, $nome. É um imenso prazer ter você por aqui!\n")
            menuPrincipal()
            return
        } else {
            tentativas++
            val restantes = 3 - tentativas
            if (restantes > 0) {
                println("Senha incorreta. Você tem $restantes tentativa(s) restante(s).")
            }
        }
    }

    println("\n Sistema bloqueado. Número máximo de tentativas atingido.")
    println("Entre em contato com o administrador.")
}

fun menuPrincipal() {
    while (true) {
        val nome = EstadoGlobal.nomeUsuario

        println("\n==================================")
        println("     HOTEL $NOME_HOTEL — MENU   ")
        println("==================================")
        println("  1. Reservas de Quartos          ")
        println("  2. Cadastro de Hóspedes         ")
        println("  3. Eventos                      ")
        println("  4. Ar-Condicionado              ")
        println("  5. Abastecimento                ")
        println("  6. Relatórios Operacionais      ")
        println("  7. Sair                         ")
        println("==================================")
        print("$nome, escolha uma opção: ")

        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> reservasDeQuartos()
            2 -> cadastrarHospedes()
            3 -> eventos()
            4 -> arCondicionado()
            5 -> abastecimento()
            6 -> relatoriosOperacionais()
            7 -> {
                println("\nMuito obrigado e até logo, $nome.")
                return
            }
            else -> erroMenu()
        }
    }
}

fun erroMenu() {
    println("Opção inválida. Por favor, digite um número entre 1 e 7.")
}
