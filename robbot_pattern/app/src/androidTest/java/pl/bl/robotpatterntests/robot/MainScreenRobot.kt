package pl.bl.robotpatterntests.robot

import pl.bl.robotpatterntests.domain.model.Hero

fun inMainScreen(action: MainScreen.() -> Unit) {
    MainScreen().action()
}

class MainScreen {
    fun assertSearchButtonDisplayed() {
        TODO("not implemented")
    }

    fun assertSearchEditTextDisplayed() {
        TODO("not implemented")
    }

    fun clickOnSearchButton() {
        TODO("not implemented")
    }

    fun inputSearch(query: String) {
        TODO("not implemented")
    }

    fun assertSearchResultDisplayed(heroes: Collection<Hero>) {
        TODO("not implemented")
    }

    fun assertSearchResultDisplayed(hero: Hero) {
        TODO("not implemented")
    }

    fun assertSearchResultNotDisplayed(heroes: Collection<Hero>) {
        TODO("not implemented")
    }

    fun assertNoResultDisplayed() {
        TODO("not implemented")
    }

    fun assertErrorDisplayed() {
        TODO("not implemented")
    }

    fun assertLoadingDisplayed() {
        TODO("not implemented")
    }

    fun clickOnSearchItem(hero: Hero) {
        TODO("not implemented")
    }
}