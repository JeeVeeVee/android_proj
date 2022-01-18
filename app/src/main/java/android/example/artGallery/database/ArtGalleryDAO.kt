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


}