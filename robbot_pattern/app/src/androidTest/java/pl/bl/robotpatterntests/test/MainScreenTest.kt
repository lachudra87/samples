package pl.bl.robotpatterntests.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.bl.robotpatterntests.data.HEROES
import pl.bl.robotpatterntests.data.HERO_1
import pl.bl.robotpatterntests.robot.inApp
import pl.bl.robotpatterntests.robot.inMainScreen
import pl.bl.robotpatterntests.ui.MainActivity
import pl.bl.robotpatterntests.domain.model.Hero
import pl.bl.robotpatterntests.robot.inDetailsScreen

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

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
    GIVEN App user
    WHEN  User opens application
    THEN  search box and search button displayed
     */
    @Test
    fun should_display_search_view_objects_when_launched() {
        // WHEN
        inApp {
            launchActivity(activityRule)
        }

        // THEN
        inMainScreen {
            assertSearchButtonDisplayed()
            assertSearchEditTextDisplayed()
        }
    }

    /**
    GIVEN Opened application, search data provided
    WHEN  User press search
    THEN  Search result displayed
     */
    @Test
    fun should_display_all_search_results_when_search_button_pressed_and_no_query() {
        // GIVEN
        inApp {
            heroesAvailable(HEROES)
            launchActivity(activityRule)
        }

        // WHEN
        inMainScreen {
            clickOnSearchButton()
        }

        // THEN
        inMainScreen {
            assertSearchResultDisplayed(HEROES)
        }
    }

    /**
    GIVEN Opened application, search data provided
    WHEN  User searches for existing hero
    THEN  Search result displayed
     */

    @Test
    fun should_display_results_for_query_when_search_button_pressed() {
        // GIVEN
        inApp {
            heroesAvailable(HEROES)
            launchActivity(activityRule)
        }

        // WHEN
        inMainScreen {
            inputSearch(HEROES[0].name)
            clickOnSearchButton()
        }

        // THEN
        inMainScreen {
            assertSearchResultDisplayed(HEROES[0])
            assertSearchResultNotDisplayed(HEROES.filter { it.id != HEROES[0].id })
        }
    }

    /**
    GIVEN Opened application, search data provided
    WHEN  User searches for non-existing hero
    THEN  No result info displayed
     */
    @Test
    fun should_display_no_results_info_when_no_items_match_query() {
        // GIVEN
        inApp {
            heroesAvailable(listOf(Hero(1, "a", "Some description")))
            launchActivity(activityRule)
        }

        // WHEN
        inMainScreen {
            inputSearch("b")
            clickOnSearchButton()
        }

        // THEN
        inMainScreen {
            assertNoResultDisplayed()
        }
    }

    /**
    GIVEN Opened application, search data error
    WHEN  User searches for hero
    THEN  Error displayed
     */
    @Test
    fun should_display_error_when_cannot_fetch_data() {
        // GIVEN
        inApp {
            heroesError(Exception())
            launchActivity(activityRule)
        }

        // WHEN
        inMainScreen {
            clickOnSearchButton()
        }

        // THEN
        inMainScreen {
            assertErrorDisplayed()
        }
    }

    /**
    GIVEN Opened application
    WHEN  User searches for hero
    THEN  Loading indicator displayed
     */
    @Test
    fun should_show_loading_when_loading_data() {
        // GIVEN
        inApp {
            heroesLoading()
            launchActivity(activityRule)
        }

        // WHEN
        inMainScreen {
            clickOnSearchButton()
        }

        // THEN
        inMainScreen {
            assertLoadingDisplayed()
        }
    }

    /**
    GIVEN Search results displayed
    WHEN  User press on search item
    THEN  Hero details screen opened
     */
    @Test
    fun should_open_hero_details_when_click_on_search_result() {
        // GIVEN
        inApp {
            heroesAvailable(listOf(HERO_1))
            launchActivity(activityRule)
        }
        inMainScreen {
            clickOnSearchButton()
        }

        // WHEN
        inMainScreen {
            clickOnSearchItem(HERO_1)
        }

        // THEN
        inDetailsScreen {
            assertDisplayed()
        }
    }

}