package android.example.artGallery.screens.home_artists

import android.example.artGallery.database.Artist
import android.example.artGallery.database.ArtworkPicture
import android.example.artGallery.databinding.ArtistItemViewBinding
import android.example.artGallery.databinding.ArtworkItemViewBinding
import android.example.artGallery.network.api_models_artists.ArtistsApiItem
import android.example.artGallery.screens.home_artworks.ArtworksAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ArtistAdapter(val clickListener: ArtistClickListener, viewModel: ArtistViewModel)
    : RecyclerView.Adapter<ArtistAdapter.CustomViewHolder>() {

    var artists = viewModel.apiResponse.value


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = artists?.get(position)

        if (item != null) {
            holder.bind(clickListener, item)
        }
    }

    override fun getItemCount(): Int {
        if(artists != null)
            return artists!!.size
        else
            return 0
    }



    class CustomViewHolder(val binding: ArtistItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: ArtistClickListener, item: ArtistsApiItem){
            binding.artist = item
            binding.clickListener = clickListener
            binding.artistDescription.text = item.type
            Picasso.get().load(item.picture).into(binding.artistImage)
        }
        companion object {
            fun from(parent: ViewGroup): CustomViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ArtistItemViewBinding.inflate(layoutInflater, parent, false)

                return CustomViewHolder(binding)
            }
        }
    }

    class ArtistClickListener(val clickListener: (artistID:Long) -> Unit){
        fun onClick(artist: ArtistsApiItem) = clickListener(artist.id)
    }


}