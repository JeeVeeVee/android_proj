package android.example.artGallery.database.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorites_table")
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "artwork_id")
    var artworkId: Long = 0L){}