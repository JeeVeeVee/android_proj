package android.example.artGallery.network.api_models

import java.io.Serializable

data class ArtworkApi(
    val id : Long,
    val title : String,
    val description : String,
    val artist : ArtistApi,
    val type : String,
    val uploadDate : String,
    val pictures : List<PictureApi>,
    val uploadUri : String,
    val numberOfImages : Int,
    val extensions : String,
    val isActive : Boolean
) : Serializable
