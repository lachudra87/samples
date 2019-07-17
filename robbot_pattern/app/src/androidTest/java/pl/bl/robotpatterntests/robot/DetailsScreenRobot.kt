package pl.bl.robotpatterntests.robot

import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import pl.bl.robotpatterntests.R
import pl.bl.robotpatterntests.domain.model.Hero

fun inDetailsScreen(action: DetailsScreen.() -> Unit) {
    DetailsScreen().action()
}

class DetailsScreen {
    fun assertDisplayed() {
        assertDisplayed(R.id.details_content)
    }

    fun assertHeroDetailsDisplayed(hero: Hero) {
        assertDisplayed(R.id.hero_name, hero.name)
        assertDisplayed(R.id.hero_description, hero.description)
    }
}