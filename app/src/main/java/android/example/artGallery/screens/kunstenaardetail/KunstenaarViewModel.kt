package android.example.artGallery.screens.kunstenaardetail

import android.example.artGallery.database.*
import android.example.artGallery.network.HooopApi
import android.example.artGallery.network.api_models.ArtworkApi
import android.example.artGallery.network.api_models.ArtworksApi
import android.example.artGallery.network.api_models_artists.ArtistsApiItem
import android.example.artGallery.network.api_models_artworkbyid.ArtworkByIdApi
import android.example.artGallery.screens.home_artworks.HooopApiStatus
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*


class KunstenaarViewModel(val database: ArtGalleryDAO, val id: Long) : ViewModel() {

    private lateinit var artistApi: ArtistsApiItem
    private lateinit var artworksLate: ArtworksApi
    val viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private var _artworks = MutableLiveData<List<ArtworkApi>>()
    val artworks: LiveData<List<ArtworkApi>>
        get() = _artworks

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> get() = _fullName

    private val _picture = MutableLiveData<String>()
    val picture: LiveData<String> get() = _picture

    private val _navigateToArtworkDetail = MutableLiveData<ArtworkApi>()
    val navigateToArtworkDetail
        get() = _navigateToArtworkDetail

    fun onArtworkClicked(artworksApi: ArtworkApi){
        _navigateToArtworkDetail.value = artworksApi
    }

    fun onArtworkDetailNavigated(){
        _navigateToArtworkDetail.value = null
    }


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getArtistFromApi(id)
                getArtworksFromAPI()
            }
            _fullName.value = artistApi.firstName + " " + artistApi.lastName
            _picture.value = artistApi.picture
            _artworks.value =
                artworksLate.artworks.filter { artworkApi -> artworkApi.artist.id == id }
        }
        println("skrbeng")


    }

    private suspend fun getArtistFromApi(id: Long) {
        val getArtistDeffered = HooopApi.retrofitService.getArtistById(id)

        try {
            val artworkResult = getArtistDeffered.await()
            artistApi = artworkResult

            println(artistApi.firstName)

        } catch (e: Exception) {
            println(e.message)
        }
    }

    private suspend fun getArtworksFromAPI() {

        val getArtworksDeferred = HooopApi.retrofitService.getArtWorks()

        try {
            val res = getArtworksDeferred.await()

            artworksLate = res

        } catch (e: Exception) {
            println(e.message)
        }
    }
}