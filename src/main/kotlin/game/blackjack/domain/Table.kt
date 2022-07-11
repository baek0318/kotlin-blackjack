package game.blackjack.domain

class Table(
    private val players: Players,
    private val getAction: (name: String) -> Boolean,
    private val showPlayerCard: (participant: Participant) -> Unit,
) {

    fun init(): Players = players.init(INIT_CARD_COUNT)

    fun distribute(): Players = players.distribute(getAction, showPlayerCard)

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
