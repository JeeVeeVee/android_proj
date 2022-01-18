package android.example.artGallery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "artwork_pictures",
    foreignKeys = [
        ForeignKey(
            entity = Artwork::class,
            parentColumns = ["artwork_id"],
            childColumns = ["artwork_id"]
        )
    ]
)
data class ArtworkPicture(
    @PrimaryKey
    val blopstring: String = "",

    @ColumnInfo(name = "artwork_id")
    val artworkId: Long = 0L,

)
