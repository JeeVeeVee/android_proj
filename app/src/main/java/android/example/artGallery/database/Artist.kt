package android.example.artGallery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists_table")
data class Artist(
    @PrimaryKey
    @ColumnInfo(name = "artist_id")
    var artistID: Long = 0L,

    var email: String = "",

    var picture: String = "",

    @ColumnInfo(name = "first_name")
    var firstName: String = "",

    @ColumnInfo(name = "last_name")
    var secondName: String = "",

    @ColumnInfo(name = "info_artist")
    var info: String =""
) {
}