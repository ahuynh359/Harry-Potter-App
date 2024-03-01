package com.ahuynh.harrypotterapp.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.ahuynh.harrypotterapp.R
import com.ahuynh.harrypotterapp.databinding.ActivityDetailBinding
import com.ahuynh.harrypotterapp.utils.Constant.KEY_HOUSE
import com.ahuynh.harrypotterapp.utils.HouseType
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        fun startActivityWithTransition(
            activity: Activity,
            imageView: ImageView,
            type: HouseType
        ) {

            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(KEY_HOUSE, type)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                imageView,
                imageView.transitionName
            )
            activity.startActivity(intent, options.toBundle())

        }
    }


    inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(
            key,
            T::class.java
        )

        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }

    private lateinit var house: HouseType
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        house = intent.serializable<HouseType>(KEY_HOUSE)!!

        binding.collapsingToolbar.title = house.name
        Glide.with(binding.root)
            .load(house.image)
            .apply(
                RequestOptions().placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error_user)
            )
            .into(binding.imvHouse)
        binding.collapsingToolbar.setExpandedTitleColor(
            ContextCompat.getColor(
                this,
                house.color
            )
        )
        binding.rcyCharacter.setBackgroundColor(ContextCompat.getColor(this, house.color))
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading)
                binding.animationLoading.visibility = View.VISIBLE
            else
                binding.animationLoading.visibility = View.INVISIBLE
        }


        setUpAdapter()
    }

    private fun setUpAdapter() {
        val itemCharacterAdapter = DetailAdapter()
        binding.rcyCharacter.adapter = itemCharacterAdapter
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen._2dp)
        binding.rcyCharacter.addItemDecoration(SpaceItemDecoration(spacingInPixels.toInt(), 3))
        viewModel.characterList.observe(this) {
            itemCharacterAdapter.submitList(it)
        }


    }
}