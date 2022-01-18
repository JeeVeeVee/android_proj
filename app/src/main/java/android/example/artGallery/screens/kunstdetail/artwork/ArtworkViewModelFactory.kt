package android.example.artGallery.screens.kunstdetail.artwork

import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.database.favorites.FavoriteDAO
import android.example.artGallery.database.favorites.FavoriteDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArtworkViewModelFactory(val database: ArtGalleryDAO, private val id: Long, val favDatabase: FavoriteDAO) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArtworkViewModel::class.java)) {
            return ArtworkViewModel(database, id, favDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}