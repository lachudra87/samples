package pl.bl.robotpatterntests.di

import pl.bl.robotpatterntests.domain.HeroesRepository
import pl.bl.robotpatterntests.domain.model.Hero
import pl.bl.robotpatterntests.local.DummyHeroesRepository

class RepositoryComponent {
    companion object {
        private val HEROES_REPOSITORY = DummyHeroesRepository(listOf(Hero(1, "Hero 1", "Hero description")))
        fun getRepository(): HeroesRepository = HEROES_REPOSITORY
    }
}