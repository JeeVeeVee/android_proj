package android.example.artGallery.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ArtGalleryDAO {

    /**
     * insert statements
     */
    @Insert
    fun insert(artwork: Artwork)

    @Insert
    fun insert(artist: Artist)

    @Insert
    fun insert(article: Article)

    @Insert
    fun insert(auction: Auction)

    @Insert
    fun insert(artworkPicture: ArtworkPicture)

    @Insert
    fun insert(offer: Offer)

    /**
     * update statements
     */
    @Update
    fun update(artwork: Artwork)

    @Update
    fun update(artist: Artist)

    @Update
    fun update(article: Article)

    @Update
    fun update(auction: Auction)

    @Update
    fun update(artworkPicture: ArtworkPicture)

    @Update
    fun update(offer: Offer)


    /**
     * queries
     */
    @Query("SELECT * FROM artworks_table")
    fun getAllArtworks(): List<Artwork>

    @Query("SELECT * FROM auctions WHERE artwork_id = :artworkKey")
    fun getAuctionByArtworkKey(artworkKey: Long) : Auction

    @Query("SELECT * FROM articles WHERE artwork_id = :artworkKey")
    fun getArticleByArtworkKey(artworkKey: Long) : Article

    @Query("SELECT * FROM artworks_table WHERE artwork_id = :artworkKey")
    fun getArtWorkByKey(artworkKey: Long): Artwork

    @Query("SELECT * FROM artists_table")
    suspend fun getAllArtists(): List<Artist>

    @Query("SELECT * FROM artworks_table WHERE artist_id = :artistKey")
    suspend fun getAllArtworksFromArtistKey(artistKey: Long) : List<Artwork>

    @Query("SELECT * FROM offers WHERE auction_id = :auctionKey")
    fun getAllOffersFromAuctionKey(auctionKey: Long) : List<Offer>

    @Query("SELECT * FROM artwork_pictures WHERE artwork_id = :artworkKey")
    fun getAllPicturesFromArtworkKey(artworkKey : Long) : List<ArtworkPicture>

    @Query("SELECT * FROM artists_table WHERE artist_id = :artistKey")
    fun getArtistByKey(artistKey : Long) : Artist


}