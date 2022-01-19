package android.example.artGallery.database.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorites_table")
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "title")
    var title: String = ""){}