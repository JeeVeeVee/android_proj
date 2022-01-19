package android.example.artGallery.screens.favorites

import android.app.Application
import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.database.Artwork
import android.example.artGallery.database.favorites.FavoriteDAO
import android.example.artGallery.network.HooopApi
import android.example.artGallery.network.HooopApiService
import android.example.artGallery.network.api_models.ArtworkApi
import android.example.artGallery.network.api_models.ArtworksApi
import android.example.artGallery.network.api_models_artworkbyid.ArtworkByIdApi

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

enum class HooopApiStatus { LOADING, ERROR, DONE }


class FavoriteImageViewModel(val database: ArtGalleryDAO, application: Application, favoriteDAO: FavoriteDAO) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _apiResponse = MutableLiveData<ArtworksApi>()
    val apiResponse: LiveData<ArtworksApi>
        get()= _apiResponse

    private var _favorites = MutableLiveData<List<ArtworkApi>>()
    val favorites :LiveData<List<ArtworkApi>>
        get() = _favorites

    private val _status = MutableLiveData<HooopApiStatus>()
    val status: LiveData<HooopApiStatus>
        get() = _status

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
            getArtworksFromAPI()
            _apiResponse.value!!.artworks.forEach{
                println(it)
            }

        }
        uiScope.launch {
            var result: List<ArtworkApi>? = null
            withContext(Dispatchers.IO){
                println("get here")
                if(apiResponse.value != null){
                    result = _apiResponse.value!!.artworks.filter { favoriteDAO.exists(it.id) }
                    println("filtered")
                    result!!.forEach { println(it.title) }
                }
            }
            _favorites.value = result
        }
    }

    private suspend fun getArtworksFromAPI() {
        _status.value = HooopApiStatus.LOADING
        val getArtworksDeferred = HooopApi.retrofitService.getArtWorks()

        try {
            val res = getArtworksDeferred.await()
            _status.value = HooopApiStatus.DONE
            _apiResponse.value = res


        } catch (e: Exception) {
            _status.value = HooopApiStatus.ERROR
        }
    }

}