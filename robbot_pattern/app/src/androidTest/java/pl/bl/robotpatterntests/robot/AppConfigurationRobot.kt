package pl.bl.robotpatterntests.robot

import android.app.Activity
import android.content.Intent
import androidx.test.rule.ActivityTestRule
import pl.bl.robotpatterntests.domain.model.Hero

fun inApp(action: AppConfiguration.() -> Unit) {
    AppConfiguration().action()
}

class AppConfiguration {

    fun <T : Activity> launchActivity(activityRule: ActivityTestRule<T>) {
        activityRule.launchActivity(Intent())
    }

    fun heroesAvailable(heroes: Collection<Hero>) {
        TODO("not implemented")
    }

    fun heroesLoading() {
        TODO("not implemented")
    }

    fun heroesError(error: Throwable) {
        TODO("not implemented")
    }

    fun clean() {
        // CLEAN ALL APP CONFIGURATIONS
    }
}