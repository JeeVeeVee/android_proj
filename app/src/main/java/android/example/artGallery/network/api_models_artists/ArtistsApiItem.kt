package android.example.artGallery.network.api_models_artists

data class ArtistsApiItem(
    val artworks: List<Any>,
    val bio: String,
    val email: String,
    val firstName: String,
    val id: Long,
    val lastName: String,
    val orders: List<Any>,
    val picture: String,
    val role: Int,
    val subscription: Any,
    val type: String
)