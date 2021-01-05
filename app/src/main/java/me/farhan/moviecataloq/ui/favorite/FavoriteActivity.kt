package me.farhan.moviecataloq.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_favorite.*
import me.farhan.moviecataloq.R

/**
 * @author farhan
 * created at at 14:28 on 28/11/20.
 */
class FavoriteActivity : AppCompatActivity() {

  private lateinit var adapter: FavoriteAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_favorite)

    setSupportActionBar(toolbar_favorite)
    supportActionBar?.title = resources.getString(R.string.favorites)

    adapter = FavoriteAdapter(this)
    viewPager2_favorite.adapter = adapter
    viewPager2_favorite.offscreenPageLimit = 2

    TabLayoutMediator(tabLayout_favorite, viewPager2_favorite) { tab, position ->
      when (position) {
        0 -> tab.text = resources.getString(R.string.movies)
        1 -> tab.text = resources.getString(R.string.tv_shows)
      }
    }.attach()
  }
}