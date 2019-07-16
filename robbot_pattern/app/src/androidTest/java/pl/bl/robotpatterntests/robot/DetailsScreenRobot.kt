package pl.bl.robotpatterntests.robot

import pl.bl.robotpatterntests.domain.model.Hero

fun inDetailsScreen(action: DetailsScreen.() -> Unit) {
    DetailsScreen().action()
}

class DetailsScreen {
    fun assertDisplayed() {
        TODO("not implemented")
    }

    fun assertHeroDetailsDisplayed(hero: Hero) {
        TODO("not implemented")
    }
}