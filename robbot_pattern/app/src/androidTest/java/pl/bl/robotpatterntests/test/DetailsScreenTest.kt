package pl.bl.robotpatterntests.test

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.bl.robotpatterntests.data.HERO_1
import pl.bl.robotpatterntests.domain.model.Hero
import pl.bl.robotpatterntests.robot.inApp
import pl.bl.robotpatterntests.robot.inDetailsScreen
import pl.bl.robotpatterntests.robot.inMainScreen
import pl.bl.robotpatterntests.ui.MainActivity

class DetailsScreenTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    private lateinit var uiDevice: UiDevice

    @Before
    fun setUp() {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        uiDevice.setOrientationNatural()
    }

    @After
    fun tearDown() {
        inApp {
            clean()
        }
    }

    /**
    GIVEN Search results displayed
    WHEN  User press on search item
    THEN  Hero details displayed
     */
    @Test
    fun should_show_hero_details_when_launched() {
        // WHEN
        openDetailsForHero(HERO_1)

        // THEN
        inDetailsScreen {
            assertHeroDetailsDisplayed(HERO_1)
        }
    }

    private fun openDetailsForHero(hero: Hero) {
        inApp {
            heroesAvailable(listOf(hero))
            launchActivity(activityRule)
        }
        inMainScreen {
            clickOnSearchButton()
            clickOnSearchItem(hero)
        }
    }
}