package pl.bl.robotpatterntests.robot

import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import pl.bl.robotpatterntests.R
import pl.bl.robotpatterntests.domain.model.Hero

fun inMainScreen(action: MainScreen.() -> Unit) {
    MainScreen().action()
}

class MainScreen {
    fun assertSearchButtonDisplayed() {
        assertDisplayed(R.id.search_button)
    }

    fun assertSearchEditTextDisplayed() {
        assertDisplayed(R.id.search_text)
    }

    fun clickOnSearchButton() {
        clickOn(R.id.search_button)
    }

    fun inputSearch(query: String) {
        writeTo(R.id.search_text, query)
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
        assertDisplayed(R.id.no_data_info)
    }

    fun assertErrorDisplayed() {
        assertDisplayed(R.id.error_info)
    }

    fun assertLoadingDisplayed() {
        assertDisplayed(R.id.loading_indicator)
    }

    fun clickOnSearchItem(hero: Hero) {
        TODO("not implemented")
    }
}