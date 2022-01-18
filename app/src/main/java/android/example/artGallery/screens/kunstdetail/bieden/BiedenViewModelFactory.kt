package android.example.artGallery.screens.kunstdetail.bieden

import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.screens.kunstdetail.auction.AuctionViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BiedenViewModelFactory(val database: ArtGalleryDAO, private val id: Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BiedenViewModel::class.java)) {
            return AuctionViewModel(database, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}