package pl.bl.robotpatterntests.ui.adapter

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import pl.bl.robotpatterntests.domain.model.Hero

class HeroesAdapter : GroupAdapter<ViewHolder>() {
    fun updateHeroes(heroes: Collection<Hero>) {
        update(heroes.map { HeroesListItem(it) })
    }
}