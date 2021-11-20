package blackjack.controller

import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Gamer
import blackjack.model.Gamers
import blackjack.model.Name
import blackjack.model.Player
import blackjack.view.DrawAction
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {

    private val inputView = InputView()
    private val outputView = OutputView()

    private var deck: Deck = Deck.empty()

    fun play() {
        var gamers = createGamers()
        deck = Deck.shuffled()
        gamers = drawAll(gamers)
        outputView.printFirstDraw(gamers, FIRST_DRAW_COUNT)
        gamers = drawWhile(gamers) { player -> outputView.printCards(player) }
        outputView.printResult(gamers)
    }

    private fun createGamers(): Gamers {
        val names = inputView.getNames() ?: return Gamers.empty()
        val players = names.toList().map { Player.from(it) }
        val dealer = Dealer.from(Name.valueOf("딜러"))
        return Gamers.from(dealer, players)
    }

    private fun drawAll(gamers: Gamers): Gamers = gamers.receiveAll(count = FIRST_DRAW_COUNT, next = ::peekAndDraw)

    private fun drawWhile(gamers: Gamers, onReceive: (Gamer) -> Unit): Gamers = gamers.receiveWhile(
        next = { gamer ->
            if (deck.isNotEmpty() && askDraw(gamer)) {
                peekAndDraw()
            } else {
                null
            }
        },
        onReceive = onReceive
    )

    private fun askDraw(gamer: Gamer): Boolean = when (gamer) {
        is Player -> inputView.askDraw(gamer) == DrawAction.YES
        is Dealer -> gamer.canReceive()
    }

    private fun peekAndDraw(): Card? {
        val card = deck.peek() ?: return null
        deck = deck.draw()
        return card
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}