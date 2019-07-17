package pl.bl.robotpatterntests.ui.adapter

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search_item.view.*
import pl.bl.robotpatterntests.R
import pl.bl.robotpatterntests.domain.model.Hero

class HeroesListItem(
    val myHero: Hero
) : Item(myHero.id.toLong()) {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.hero_name.text = myHero.name
        viewHolder.itemView.tag = myHero.id // add ID as tag for possibility to scroll to element in android tests
    }

    override fun getLayout(): Int = R.layout.search_item

}