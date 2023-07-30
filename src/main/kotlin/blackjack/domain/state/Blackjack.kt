package blackjack.domain.state

import blackjack.domain.Deck
import blackjack.domain.Score

class Blackjack(private val deck: Deck) : State {
    override fun score(): Score {
        return deck.score()
    }

    override fun currentDeck(): Deck {
        return deck
    }
}