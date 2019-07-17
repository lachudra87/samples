package pl.bl.robotpatterntests.ui.adapter

import android.content.Context
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import pl.bl.robotpatterntests.domain.model.Hero
import pl.bl.robotpatterntests.ui.DetailsActivity

class HeroesAdapter(
    private val context: Context
) : GroupAdapter<ViewHolder>() {
    init {
        setOnItemClickListener { item, _ ->
            onItemClicked((item as HeroesListItem).myHero)
        }
    }

    fun updateHeroes(heroes: Collection<Hero>) {
        update(heroes.map { HeroesListItem(it) })
    }

    private fun onItemClicked(hero: Hero) {
        context.startActivity(DetailsActivity.launchIntent(context, hero))
    }
}