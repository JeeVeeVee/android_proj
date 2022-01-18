package android.example.artGallery.screens.kunstdetail.auction

import android.example.artGallery.database.ArtGalleryDAO
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuctionViewModelFactory(val database: ArtGalleryDAO, private val id: Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AuctionViewModel::class.java)) {
            return AuctionViewModel(database, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}