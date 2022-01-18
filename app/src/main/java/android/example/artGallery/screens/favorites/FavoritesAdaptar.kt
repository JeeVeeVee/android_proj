package android.example.artGallery.screens.favorites

import android.example.artGallery.databinding.ArtworkItemViewBinding
import android.example.artGallery.network.api_models.ArtworkApi
import android.example.artGallery.screens.home_artworks.ArtworkImageViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FavoritesAdaptar(val clickListener: ArtworksListener, val viewModel: ArtworkImageViewModel): RecyclerView.Adapter<FavoritesAdaptar.CustomViewHolder>() {

    var artworks = viewModel.apiResponse.value?.artworks

    override fun getItemCount(): Int {
        if (artworks != null) {
            return artworks!!.size
        }else{
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



    class CustomViewHolder(val binding: ArtworkItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: ArtworksListener, item: ArtworkApi){
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

    class ArtworksListener(val clickListener: (artworkApi: ArtworkApi) -> Unit){
        fun onClick(artwork: ArtworkApi) = clickListener(artwork)
    }

}