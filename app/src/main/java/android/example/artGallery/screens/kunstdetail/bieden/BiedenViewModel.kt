package android.example.artGallery.screens.kunstdetail.bieden

import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.database.ArtworkPicture
import android.example.artGallery.database.Offer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.util.*

class BiedenViewModel(val database: ArtGalleryDAO, val id: Long) : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _artist = MutableLiveData<String>()
    val artist: LiveData<String> get() = _artist

    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> get() = _offers

    private val _pictures = MutableLiveData<List<ArtworkPicture>>()
    val pictures: LiveData<List<ArtworkPicture>> get() = _pictures

    init{
        val viewModelJob = Job()

        val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        uiScope.launch {
            withContext(Dispatchers.IO) {
                val artwork = database.getArtWorkByKey(id)
                val auction = database.getAuctionByArtworkKey(id)
                val pictures = database.getAllPicturesFromArtworkKey(id)
                val artist = database.getArtistByKey(artwork.artistId)
                val offers = database.getAllOffersFromAuctionKey(auction.auctionId)

                _title.value = artwork.title
                _artist.value = artist.firstName + " " + artist.secondName
                _offers.value = offers
                _pictures.value = pictures
            }
        }

    }

    fun CreateOffer(price: Double){
        //TODO API call aanmaken om een offer aan te maken
    }
}