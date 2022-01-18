package android.example.artGallery.network.api_models_artworkbyid

data class ArtistByIdApi(
    val bio: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val picture: String,
    val role: Int,
    val subscription: Any,
    val type: String
)