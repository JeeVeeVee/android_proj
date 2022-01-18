package android.example.artGallery.network.api_models


data class ArtistApi(
    val id : Long,
    val email : String,
    val firstName : String,
    val bio : String,
    val type : String,
    val role : Int,
    val lastName : String,
    val picture : String,
    val subscription : String
)
