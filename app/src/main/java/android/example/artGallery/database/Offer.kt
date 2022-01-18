package android.example.artGallery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "offers",
    foreignKeys = [
        ForeignKey(
            entity = Auction::class,
            parentColumns = ["auction_id"],
            childColumns = ["auction_id"]
        )
    ]
)
data class Offer(
    @PrimaryKey(autoGenerate = true)
    var offerId: Long = 0L,

    @ColumnInfo(name = "auction_id")
    var auctionId: Long = 0L,

    @ColumnInfo(name = "price")
    var price : Double,

    @ColumnInfo(name = "user_id")
    var userId : Long
)
