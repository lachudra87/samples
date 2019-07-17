package pl.bl.robotpatterntests.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*
import pl.bl.robotpatterntests.R
import pl.bl.robotpatterntests.domain.model.Hero

class DetailsActivity : AppCompatActivity() {

    companion object {
        private const val HERO_TAG = "HERO"
        fun launchIntent(context: Context, hero: Hero): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(HERO_TAG, hero)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun onResume() {
        super.onResume()
        val hero = intent.getParcelableExtra<Hero>(HERO_TAG)
        if (hero != null) {
            setData(hero)
        } else {
            finish()
        }
    }

    private fun setData(hero: Hero) {
        hero_description.text = hero.description
        hero_name.text = hero.name
    }
}
