package android.example.artGallery.screens.kunstdetail.artwork

import android.example.artGallery.database.*
import android.example.artGallery.database.favorites.Favorite
import android.example.artGallery.database.favorites.FavoriteDAO
import android.example.artGallery.database.favorites.FavoriteDatabase
import android.example.artGallery.network.HooopApi
import android.example.artGallery.network.api_models_artworkbyid.ArtworkById
import android.example.artGallery.network.api_models_artworkbyid.ArtworkByIdApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class ArtworkViewModel(
    val artDatabase: ArtGalleryDAO,
    val id: Long,
    val favDatabase: FavoriteDAO?
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private lateinit var artworkApi: ArtworkByIdApi
    private lateinit var _artwork: Artwork


    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _artist = MutableLiveData<String>()
    val artist: LiveData<String> get() = _artist

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _material = MutableLiveData<String>()
    val material: LiveData<String> get() = _material

    private val _measurements = MutableLiveData<String>()
    val measurements: LiveData<String> get() = _measurements

    private val _weight = MutableLiveData<Double>()
    val weight: LiveData<Double> get() = _weight

    private val _pictures = MutableLiveData<List<ArtworkPicture>>()
    val pictures: LiveData<List<ArtworkPicture>> get() = _pictures

    private val _isFavorite = MutableLiveData(true)
    val isFavorite : LiveData<Boolean> get() = _isFavorite

    init {
        viewModelScope.launch {
            getArtworkFromAPI(id)
            _artwork = Artwork(
                artworkApi.id.toLong(),
                artworkApi.artist.id.toLong(),
                artworkApi.title,
                artworkApi.description,
                artworkApi.type,
                artworkApi.uploadDate,
                artworkApi.materiaal
            )
            if (favDatabase != null) {
                _isFavorite.value = favDatabase.exists(id).value
            }
        }

    }

    private suspend fun getArtworkFromAPI(id: Long) {
        val getArtworkDeferred = HooopApi.retrofitService.getArtworkById(id.toInt())

        try {
            val artworkResult = getArtworkDeferred.await()
            artworkApi = artworkResult.artwork

        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun removeFav(id: Int) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val favorite = Favorite(artworkId = id.toLong())
                delete(favorite)
            }
        }
    }


    fun addFav(id: Int) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val favorite = Favorite(artworkId = id.toLong())
                insert(favorite)
            }
        }

    }

    private suspend fun insert(favorite: Favorite) {
        withContext(Dispatchers.IO) {
            favDatabase?.insert(favorite)
        }
    }

    private suspend fun delete(favorite: Favorite) {
        withContext(Dispatchers.IO) {
            favDatabase?.delete(favorite)
        }
    }
}