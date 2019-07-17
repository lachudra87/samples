package pl.bl.robotpatterntests.local

import pl.bl.robotpatterntests.domain.HeroesCallback
import pl.bl.robotpatterntests.domain.HeroesRepository
import pl.bl.robotpatterntests.domain.model.Hero

class DummyHeroesRepository(
    private val heroes: Collection<Hero>
) : HeroesRepository {
    override fun getHeroes(callback: HeroesCallback) {
        callback.onData(heroes)
    }
}