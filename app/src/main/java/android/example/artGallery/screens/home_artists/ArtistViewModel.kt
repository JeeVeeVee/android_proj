package android.example.artGallery.screens.home_artists

import android.app.Application
import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.network.HooopApi
import android.example.artGallery.network.api_models_artists.ArtistsApiItem
import android.example.artGallery.screens.home_artworks.HooopApiStatus
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll

class ArtistViewModel(val database: ArtGalleryDAO, application: Application): AndroidViewModel(application) {

    private var _apiResponse = MutableLiveData<List<ArtistsApiItem>>()
    val apiResponse: LiveData<List<ArtistsApiItem>>
        get()= _apiResponse

    private val _status = MutableLiveData<HooopApiStatus>()
    val status: LiveData<HooopApiStatus>
        get() = _status

    private val _navigateToArtistDetail = MutableLiveData<Long>()
    val navigateToArtistDetail
        get() = _navigateToArtistDetail

    fun onArtistClicked(id: Long){
        _navigateToArtistDetail.value = id
    }

    fun onArtworkDetailNavigated(){
        _navigateToArtistDetail.value = null
    }

    init {
        viewModelScope.launch {
            getArtistsFromApi()
        }
    }

    private suspend fun getArtistsFromApi() {
        _status.value = HooopApiStatus.LOADING
        val getArtistsApi = HooopApi.retrofitService.getArtists()

        try {
            val res = getArtistsApi.await()
            _status.value = HooopApiStatus.DONE
            _apiResponse.value = res

        } catch (e: Exception) {
            _status.value = HooopApiStatus.ERROR
            println(e.message)
        }
    }
}