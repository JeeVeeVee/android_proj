package android.example.artGallery.screens.favorites

import android.app.Application
import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.screens.home_artworks.ArtworkImageViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteImageViewModelFactory(
    private val dataSource: ArtGalleryDAO,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteImageViewModel::class.java)) {
            return FavoriteImageViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class!")
    }
}