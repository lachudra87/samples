package pl.bl.robotpatterntests.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pl.bl.robotpatterntests.R
import pl.bl.robotpatterntests.di.RepositoryComponent
import pl.bl.robotpatterntests.domain.HeroesCallback
import pl.bl.robotpatterntests.domain.model.Hero
import pl.bl.robotpatterntests.ui.adapter.HeroesAdapter


class MainActivity : AppCompatActivity() {

    private val repository = RepositoryComponent.getRepository()
    private val adapter = HeroesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        search_result.adapter = adapter
        search_result.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        search_button.setOnClickListener { onSearchClicked() }
    }

    private fun displayError() {
        error_info.visibility = View.VISIBLE
        search_result.visibility = View.GONE
        no_data_info.visibility = View.GONE
        loading_indicator.visibility = View.GONE
    }

    private fun displayLoading() {
        loading_indicator.visibility = View.VISIBLE
    }

    private fun displayData(heroes: Collection<Hero>) {
        adapter.updateHeroes(heroes)
        error_info.visibility = View.GONE
        search_result.visibility = if (heroes.isNotEmpty()) View.VISIBLE else View.GONE
        no_data_info.visibility = if (heroes.isEmpty()) View.VISIBLE else View.GONE
        loading_indicator.visibility = View.GONE
    }

    private fun onSearchClicked() {
        hideKeyboard()
        val query = search_text.text.toString()
        displayLoading()
        repository.getHeroes(object : HeroesCallback {
            override fun onError(error: Throwable) {
                displayError()
            }

            override fun onData(data: Collection<Hero>) {
                if (query.isEmpty()) {
                    displayData(data)
                } else {
                    val filteredData = data.filter { it.name.contains(query) }
                    displayData(filteredData)
                }
            }

        })
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
