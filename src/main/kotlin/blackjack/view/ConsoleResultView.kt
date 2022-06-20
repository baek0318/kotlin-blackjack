package blackjack.view

import blackjack.model.player.Player
import blackjack.model.player.Players

object ConsoleResultView : ResultView {
    override fun printPlayersCardStatus(players: Players) {
        println()

        with(StringBuilder()) {
            players.players.map { this.append("${it.name}에게 ${it.cardSize}장을 ") }
            this.append("나누었습니다.")
            println(this)
        }

        players.players.map { printPlayerCardStatus(it) }

        println()
    }

    override fun printPlayerCardStatus(player: Player) = println(playerCardStatus(player))

    override fun printCardGameResult(players: Players) {
        println()

        players.players.map {
            println("${playerCardStatus(it)} - 결과: ${it.sumOfCardScore}")
        }
    }

    private fun playerCardStatus(player: Player) = "${player.name}카드: ${player.cards}"
}
