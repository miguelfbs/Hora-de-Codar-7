package hotel

import java.util.Locale.getDefault

fun abastecimento() {
    val nome = EstadoGlobal.nomeUsuario
    println("\n===============")
    println(" Abastecimento ")
    println("===============")

    val TANQUE = 42.0

    println("── Wayne Oil ──────────────────────")
    val waynAlcool   = readDouble("  Álcool   (R$/L): ")
    val wayneGasolina = readDouble("  Gasolina (R$/L): ")

    println("── Stark Petrol ───────────────────")
    val starkAlcool   = readDouble("  Álcool   (R$/L): ")
    val starkGasolina = readDouble("  Gasolina (R$/L): ")

    val (wayneCombustivel, wayneCusto) = melhorCombustivel(waynAlcool, wayneGasolina, TANQUE)
    val (starkCombustivel, starkCusto) = melhorCombustivel(starkAlcool, starkGasolina, TANQUE)

    println("\nWayne Oil   : melhor opção = $wayneCombustivel | Total (42L) = ${formatarMoeda(wayneCusto)}")
    println("Stark Petrol: melhor opção = $starkCombustivel | Total (42L) = ${formatarMoeda(starkCusto)}")

    val (melhorPosto, melhorCombustivel2) = if (wayneCusto <= starkCusto) {
        Pair("Wayne Oil", wayneCombustivel)
    } else {
        Pair("Stark Petrol", starkCombustivel)
    }

    println("\n$nome, é mais barato abastecer com ${melhorCombustivel2.lowercase(getDefault())} no posto $melhorPosto.")
}

fun melhorCombustivel(precoAlcool: Double, precoGasolina: Double, tanque: Double): Pair<String, Double> {
    return if (precoAlcool <= precoGasolina * 0.70) {
        Pair("Álcool", precoAlcool * tanque)
    } else {
        Pair("Gasolina", precoGasolina * tanque)
    }
}
