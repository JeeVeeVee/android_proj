package android.example.artGallery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "articles",
    foreignKeys = [
        ForeignKey(
            entity = Artwork::class,
            parentColumns = ["artwork_id"],
            childColumns = ["artwork_id"]
        )
    ]
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    var articleId: Long = 0L,

    @ColumnInfo(name = "artwork_id")
    var artworkId: Long,
    var price: Double,
    var isSold: Boolean,
    var sellDate: Date?,
    var weight: Double,
    var measurements: String
)

