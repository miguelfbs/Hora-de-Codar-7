# Hotel Terabithia — Sistema de Gerenciamento

Sistema de gerenciamento hoteleiro desenvolvido em Kotlin, com funcionalidades de reservas, cadastro de hóspedes, eventos, orçamentos de ar-condicionado, abastecimento e relatórios operacionais.

---

## Como executar

### Pré-requisitos

- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) instalado (Community ou Ultimate)
- JDK 11 ou superior (o próprio IntelliJ pode instalar automaticamente)

### Passo a passo

1. **Baixe os arquivos** do repositório (botão `Code > Download ZIP` ou via `git clone`)
2. **Abra o IntelliJ IDEA**
3. Clique em **File > Open** e selecione a pasta onde os arquivos foram salvos
4. Aguarde o IntelliJ indexar o projeto
5. Abra o arquivo **`Hotel.kt`**
6. Clique no ícone **▶ Run** (triângulo verde) ao lado da função `fun main()`, ou pressione `Shift + F10`

O sistema será iniciado no terminal integrado do IntelliJ.

---

## Arquivos do projeto

| Arquivo | Descrição |
|---|---|
| `Hotel.kt` | Ponto de entrada — autenticação e menu principal |
| `EstadoGlobal.kt` | Estado compartilhado entre os módulos |
| `Reservas.kt` | Reservas de quartos |
| `CadastrarHospedes.kt` | Cadastro, pesquisa e gerenciamento de hóspedes |
| `Eventos.kt` | Reserva de auditórios e cálculo de eventos |
| `ArCondicionado.kt` | Comparativo de orçamentos de ar-condicionado |
| `Abastecimento.kt` | Comparativo de preços de combustível |
| `Relatorios.kt` | Relatórios operacionais consolidados |

---

## Credenciais de acesso

- **Senha:** `2678`
