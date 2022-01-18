package android.example.artGallery.screens.kunstdetail.article

import android.example.artGallery.database.*
import android.example.artGallery.network.HooopApi
import android.example.artGallery.network.api_models.ArtworkApi
import android.example.artGallery.network.api_models.ArtworksApi
import android.example.artGallery.network.api_models_artworkbyid.ArtworkById
import android.example.artGallery.screens.home_artworks.HooopApiStatus
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class ArticleViewModel(val database: ArtGalleryDAO, val id: Long) : ViewModel() {

    private var _apiResponse = MutableLiveData<ArtworkById>()
    val apiResponse: LiveData<ArtworkById>
        get()= _apiResponse

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _artist = MutableLiveData<String>()
    val artist: LiveData<String> get() = _artist

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> get() = _price

    private val _material = MutableLiveData<String>()
    val material: LiveData<String> get() = _material

    private val _measurements = MutableLiveData<String>()
    val measurements: LiveData<String> get() = _measurements

    private val _weight = MutableLiveData<Double>()
    val weight: LiveData<Double> get() = _weight

    private val _pictures = MutableLiveData<List<ArtworkPicture>>()
    val pictures: LiveData<List<ArtworkPicture>> get() = _pictures

    init{
        viewModelScope.launch {
            getArtworkFromAPI(id)
        }
/*        val artwork = database.getArtWorkByKey(id)
        val article = database.getArticleByArtworkKey(id)
        val pictures = database.getAllPicturesFromArtworkKey(id)
        val artist = database.getArtistByKey(artwork.artistId)

        _title.value = artwork.title
        _description.value = artwork.description
        _artist.value = artist.firstName + " " + artist.secondName
        _price.value = article.price
        _measurements.value = article.measurements
        _material.value = artwork.material
        _weight.value = article.weight
        _pictures.value = pictures*/

    }


    private suspend fun getArtworkFromAPI(id: Long) {
        val getArtworkDeferred = HooopApi.retrofitService.getArtworkById(id.toInt())

        try {
            val res = getArtworkDeferred.await()
            _apiResponse.value = res
        } catch (e: Exception) {
            println(e.message)
        }
    }
}