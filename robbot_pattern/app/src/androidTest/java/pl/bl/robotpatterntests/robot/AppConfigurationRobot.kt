package pl.bl.robotpatterntests.robot

import android.app.Activity
import android.content.Intent
import androidx.test.rule.ActivityTestRule
import pl.bl.robotpatterntests.di.RepositoryComponent
import pl.bl.robotpatterntests.domain.model.Hero
import pl.bl.robotpatterntests.mock.HeroesRepositoryVisitor
import pl.bl.robotpatterntests.mock.TestHeroesRepository

fun inApp(action: AppConfiguration.() -> Unit) {
    AppConfiguration().action()
}

class AppConfiguration {

    fun <T : Activity> launchActivity(activityRule: ActivityTestRule<T>) {
        activityRule.launchActivity(Intent())
    }

    fun heroesAvailable(heroes: Collection<Hero>) {
        getHeroesTestRepository()
            .apply(HeroesRepositoryVisitor.DataAvailable(heroes))
    }

    fun heroesLoading() {
        getHeroesTestRepository()
            .apply(HeroesRepositoryVisitor.Loading())
    }

    fun heroesError(error: Throwable) {
        getHeroesTestRepository()
            .apply(HeroesRepositoryVisitor.Error(error))
    }

    fun clean() {
        // CLEAN ALL APP CONFIGURATIONS
        getHeroesTestRepository()
            .clean()
    }

    private fun getHeroesTestRepository() = RepositoryComponent.getRepository() as TestHeroesRepository
}