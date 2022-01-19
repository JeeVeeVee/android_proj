package android.example.artGallery.screens.favorites

import android.content.Context
import android.example.artGallery.database.ArtworkPicture
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import android.example.artGallery.databinding.ArtworkItemViewBinding
import android.example.artGallery.network.api_models.ArtworkApi
import android.example.artGallery.network.api_models.ArtworksApi
import android.example.artGallery.screens.home_artworks.ArtworksAdapter


class FavoritesAdaptar(val clickListener: ArtworksAdapter.ArtworksListener, val viewModel: FavoriteImageViewModel) :
    RecyclerView.Adapter<FavoritesAdaptar.CustomViewHolder>() {

    var artworks = viewModel.favorites.value

    override fun getItemCount(): Int {
        if (artworks != null) {
            return artworks!!.size
        } else {
            return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = artworks?.get(position)
        if (item != null) {
            holder.bind(clickListener, item)
        }
    }

    class CustomViewHolder(val binding: ArtworkItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: ArtworksAdapter.ArtworksListener, item: ArtworkApi) {
            binding.artwork = item
            binding.clickListener = clickListener
            Picasso.get().load(item.pictures[0].picture).into(binding.artworkImg)
        }

        companion object {
            fun from(parent: ViewGroup): CustomViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ArtworkItemViewBinding.inflate(layoutInflater, parent, false)

                return CustomViewHolder(binding)
            }
        }
    }
}