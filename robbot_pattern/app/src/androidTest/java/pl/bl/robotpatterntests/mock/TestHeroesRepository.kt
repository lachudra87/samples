package pl.bl.robotpatterntests.mock

import pl.bl.robotpatterntests.domain.HeroesCallback
import pl.bl.robotpatterntests.domain.HeroesRepository
import pl.bl.robotpatterntests.domain.model.Hero

class TestHeroesRepository : HeroesRepository {
    private val callbacks = mutableSetOf<HeroesCallback>()

    var error: Throwable? = null
    var data: Collection<Hero>? = null

    override fun getHeroes(callback: HeroesCallback) {
        callbacks.add(callback)
        dispatchPending(data, error)
    }

    fun clean() {
        callbacks.clear()
        error = null
        data = null
    }

    fun apply(visitor: HeroesRepositoryVisitor) {
        visitor.visit(this)
    }

    fun dispatchPending() {
        dispatchPending(data, error)
    }

    private fun dispatchPending(
        data: Collection<Hero>?,
        error: Throwable?
    ) {
        if (data != null) {
            dispatchData(data)
        } else if (error != null) {
            dispatchError(error)
        }

    }

    private fun dispatchError(error: Throwable) {
        val localCallbacks = mutableListOf<HeroesCallback>()
            .apply { addAll(callbacks) }
        callbacks.clear()
        localCallbacks.forEach {
            it.onError(error)
        }
    }

    private fun dispatchData(data: Collection<Hero>) {
        val localCallbacks = mutableListOf<HeroesCallback>()
            .apply { addAll(callbacks) }
        callbacks.clear()
        localCallbacks.forEach {
            it.onData(data)
        }
    }
}

interface HeroesRepositoryVisitor {
    fun visit(repository: TestHeroesRepository)

    class DataAvailable(val data: Collection<Hero>) : HeroesRepositoryVisitor {
        override fun visit(repository: TestHeroesRepository) {
            repository.error = null
            repository.data = data
            repository.dispatchPending()
        }
    }

    class Error(val error: Throwable) : HeroesRepositoryVisitor {
        override fun visit(repository: TestHeroesRepository) {
            repository.error = error
            repository.data = null
            repository.dispatchPending()
        }
    }

    class Loading : HeroesRepositoryVisitor {
        override fun visit(repository: TestHeroesRepository) {
            repository.error = null
            repository.data = null
            repository.dispatchPending()
        }
    }
}