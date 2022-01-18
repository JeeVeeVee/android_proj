package android.example.artGallery.screens.kunstdetail.auction

import android.example.artGallery.database.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class AuctionViewModel(val database: ArtGalleryDAO, val id: Long) : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _artist = MutableLiveData<String>()
    val artist: LiveData<String> get() = _artist

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _endDate = MutableLiveData<Date>()
    val endDate: LiveData<Date> get() = _endDate

    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> get() = _offers

    private  val _startPrice = MutableLiveData<Double>()
    val startPrice: LiveData<Double> get() = _startPrice

    private val _material = MutableLiveData<String>()
    val material: LiveData<String> get() = _material

    private val _measurements = MutableLiveData<String>()
    val measurements: LiveData<String> get() = _measurements

    private val _weight = MutableLiveData<Double>()
    val weight: LiveData<Double> get() = _weight

    private val _pictures = MutableLiveData<List<ArtworkPicture>>()
    val pictures: LiveData<List<ArtworkPicture>> get() = _pictures

    init{
        val artwork = database.getArtWorkByKey(id)
        val auction = database.getAuctionByArtworkKey(id)
        val pictures = database.getAllPicturesFromArtworkKey(id)
        val artist = database.getArtistByKey(artwork.artistId)
        val offers = database.getAllOffersFromAuctionKey(auction.auctionId)

        _title.value = artwork.title
        _description.value = artwork.description
        _artist.value = artist.firstName + " " + artist.secondName
        _startPrice.value = auction.startPrice
        _endDate.value = auction.endDate
        _offers.value = offers
        _measurements.value = auction.measurements
        _material.value = artwork.material
        _weight.value = auction.weight
        _pictures.value = pictures
    }
}