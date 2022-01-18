package android.example.artGallery.screens.kunstdetail.kopen

import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.database.ArtworkPicture
import android.example.artGallery.database.Offer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class KopenViewModel(val database: ArtGalleryDAO, val id: Long) : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _artist = MutableLiveData<String>()
    val artist: LiveData<String> get() = _artist

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> get() = _price

    private val _pictures = MutableLiveData<List<ArtworkPicture>>()
    val pictures: LiveData<List<ArtworkPicture>> get() = _pictures

    init{
        val viewModelJob = Job()

        val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        uiScope.launch {
            withContext(Dispatchers.IO) {

            }
        }


    }

    fun CreateOrder(){
        //TODO API call aanmaken om een offer aan te maken
    }
}