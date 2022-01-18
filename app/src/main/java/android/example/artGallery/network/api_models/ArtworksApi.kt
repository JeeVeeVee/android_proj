package android.example.artGallery.network.api_models

import android.example.artGallery.database.Artwork


data class ArtworksApi(
    val artworks : List<ArtworkApi>,
    val totalAmount : Int
)

/*fun ArtworksApi.asDatabaseArtwork(): List<Artwork> {
    return artworks.map {
        Artwork(artworkId = it.id,
        artistId = it.artist.id,
        title = it.title,
        description = it.description,
        type = it.type,
        )
    }*/
//}


