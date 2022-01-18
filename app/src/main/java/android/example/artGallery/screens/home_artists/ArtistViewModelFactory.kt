package android.example.artGallery.screens.home_artists

import android.app.Application
import android.example.artGallery.database.ArtGalleryDAO
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ArtistViewModelFactory(
    private val dataSource: ArtGalleryDAO,
    private val application: Application):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArtistViewModel::class.java)){
            return ArtistViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class!")
    }

}
