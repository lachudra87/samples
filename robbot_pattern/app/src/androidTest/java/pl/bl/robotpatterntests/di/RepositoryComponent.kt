package pl.bl.robotpatterntests.di

import pl.bl.robotpatterntests.domain.HeroesRepository
import pl.bl.robotpatterntests.mock.TestHeroesRepository

class RepositoryComponent {
    companion object {
        private val HEROES_REPOSITORY = TestHeroesRepository()
        fun getRepository(): HeroesRepository = HEROES_REPOSITORY
    }
}