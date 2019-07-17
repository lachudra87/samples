package pl.bl.robotpatterntests.domain

import pl.bl.robotpatterntests.domain.model.Hero

interface HeroesRepository {
    fun getHeroes(callback: HeroesCallback)
}

interface HeroesCallback {
    fun onData(data: Collection<Hero>)
    fun onError(error: Throwable)
}