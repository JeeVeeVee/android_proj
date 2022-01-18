package android.example.artGallery.screens

import android.example.artGallery.screens.home_artworks.ImageItemViewHolder
import android.example.artGallery.R
import android.example.artGallery.database.Artwork
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton


class ArtworkItemAdapter : RecyclerView.Adapter<ImageItemViewHolder>() {
    var data = listOf<Artwork>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        holder.imageButton.setImageResource(R.drawable.britannica)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val view = layoutInflator.inflate(R.layout.image_item_view,parent,false) as ImageButton
        return ImageItemViewHolder(view)
    }
}
