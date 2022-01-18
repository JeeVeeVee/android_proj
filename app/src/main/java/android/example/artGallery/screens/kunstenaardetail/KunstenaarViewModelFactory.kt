package android.example.artGallery.screens.kunstenaardetail

import android.example.artGallery.database.ArtGalleryDAO
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class KunstenaarViewModelFactory(val database: ArtGalleryDAO, private val id: Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(KunstenaarViewModel::class.java)) {
            return KunstenaarViewModel(database, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}