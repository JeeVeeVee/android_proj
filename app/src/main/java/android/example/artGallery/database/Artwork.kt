package android.example.artGallery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "artworks_table",
    foreignKeys = [
        ForeignKey(
            entity = Artist::class,
            parentColumns = arrayOf("artist_id"),
            childColumns = arrayOf("artist_id"),
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Artwork(
    @PrimaryKey
    @ColumnInfo(name = "artwork_id")
    val artworkId: Long = 0L,

    @ColumnInfo(name = "artist_id")
    val artistId: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "artwork_type")
    var type: String,

    @ColumnInfo(name = "upload_date")
    var date: String,

    @ColumnInfo(name = "material")
    var material: String
){

}
