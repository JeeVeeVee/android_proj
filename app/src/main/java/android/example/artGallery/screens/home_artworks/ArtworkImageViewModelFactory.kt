package android.example.artGallery.screens.home_artworks

import android.app.Application
import android.example.artGallery.database.ArtGalleryDAO
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArtworkImageViewModelFactory(
    private val dataSource: ArtGalleryDAO,
    private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtworkImageViewModel::class.java)) {
                return ArtworkImageViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class!")
        }
}