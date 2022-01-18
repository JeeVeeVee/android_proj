package android.example.artGallery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


@Entity(
    tableName = "auctions",
    foreignKeys = [
        ForeignKey(
            entity = Artwork::class,
            parentColumns = ["artwork_id"],
            childColumns = ["artwork_id"]
        )
    ]
)
data class Auction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "auction_id")
    var auctionId: Long = 0L,

    @ColumnInfo(name = "artwork_id")
    var artworkId: Long = 0L,

    @ColumnInfo(name = "start_price")
    var startPrice: Double,

    @ColumnInfo(name = "end_date")
    var endDate: Date,

    var weight: Double,

    var measurements: String
)
