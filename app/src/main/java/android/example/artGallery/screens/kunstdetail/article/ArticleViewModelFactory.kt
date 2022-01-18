package android.example.artGallery.screens.kunstdetail.article

import android.app.Application
import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.screens.home_artworks.ArtworkImageViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArticleViewModelFactory(val database: ArtGalleryDAO, private val id: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(database, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class ArtworkImageViewModelFactory(
    private val dataSource: ArtGalleryDAO,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtworkImageViewModel::class.java)) {
            return ArtworkImageViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class!")
    }
}