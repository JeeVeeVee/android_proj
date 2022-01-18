package android.example.artGallery.screens.kunstdetail.kopen

import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.screens.kunstdetail.artwork.ArtworkViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class KopenViewModelFactory(val database: ArtGalleryDAO, private val id: Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(KopenViewModel::class.java)) {
            return ArtworkViewModel(database, id, null) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}