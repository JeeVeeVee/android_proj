package android.example.artGallery.database.favorites

import android.example.artGallery.database.*
import android.example.artGallery.database.Artwork
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDAO {

    /**
     * insert statements
     */
    @Insert
    fun insert(favorite: Favorite)



    /**
     * update statements
     */
    @Update
    fun update(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)



    /**
     * queries
     */
    @Query("SELECT * FROM favorites_table")
    fun getAllFavorites(): List<Favorite>


    @Query("SELECT exists (select 1 from favorites_table where title =:title)")
    fun exists(title : String) : Boolean


}