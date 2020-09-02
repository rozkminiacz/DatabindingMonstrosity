package tech.michalik.databindingmonstrosity

import android.widget.ImageView
import androidx.databinding.BindingAdapter

interface ImageLoader {
    fun loadImageFromUrl(imageView: ImageView, url: String)
}

class GlideImageLoader : ImageLoader {
    override fun loadImageFromUrl(imageView: ImageView, url: String) {
        TODO("Not yet implemented")
    }
}

class ImageBindings(private val imageLoader: ImageLoader) : ImageLoader by imageLoader {
    @BindingAdapter("bind:image")
    fun ImageView.bindImage(imageUrl: String) {
        loadImageFromUrl(this, imageUrl)
    }
}