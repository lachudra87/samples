package pl.bl.robotpatterntests.robot

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.equalTo
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
        heroes.forEach {
            assertSearchResultDisplayed(it)
        }
    }

    fun assertSearchResultDisplayed(hero: Hero) {
        scrollToHero(hero)
        onView(
            allOf(
                withText(hero.name),
                isDescendantOfA(withTagValue(equalTo(hero.id)))
            )
        ).check(matches(isDisplayed()))
    }

    fun assertSearchResultNotDisplayed(heroes: Collection<Hero>) {
        heroes.forEach {
            assertSearchResultNotDisplayed(it)
        }

    }

    fun assertSearchResultNotDisplayed(hero: Hero) {
        onView(
            allOf(
                withText(hero.name),
                isDescendantOfA(withTagValue(equalTo(hero.id)))
            )
        ).check(doesNotExist())
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
        scrollToHero(hero)
        onView(
            allOf(
                withText(hero.name),
                isDescendantOfA(withTagValue(equalTo(hero.id)))
            )
        ).perform(click())
    }

    private fun scrollToHero(hero: Hero) {
        onView(withId(R.id.search_result))
            .perform(scrollTo<RecyclerView.ViewHolder>(withTagValue(equalTo(hero.id))))
    }
}