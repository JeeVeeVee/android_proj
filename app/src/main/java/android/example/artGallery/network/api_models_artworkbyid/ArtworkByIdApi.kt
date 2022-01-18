package android.example.artGallery.network.api_models_artworkbyid

data class ArtworkByIdApi(
    val afmetingen: String,
    val artist: ArtistByIdApi,
    val bids: List<Any>,
    val description: String,
    val endDate: String,
    val extensions: Any,
    val gewicht: Int,
    val highestBid: Any,
    val id: Int,
    val isActive: Boolean,
    val isSold: Boolean,
    val materiaal: String,
    val numberOfImages: Int,
    val pictures: List<PictureByIdApi>,
    val price: Int,
    val sellDate: String,
    val title: String,
    val type: String,
    val uploadDate: String,
    val uploadUri: Any
)