package android.example.artGallery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        Article::class,
        Artist::class,
        Artwork::class,
        ArtworkPicture::class,
        Auction::class,
        Offer::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArtGalleryDatabase : RoomDatabase() {

    abstract val artGalleryDAO: ArtGalleryDAO

    /**
     * kotlin voor statische methode
     */
    companion object {
        @Volatile
        private var INSTANCE: ArtGalleryDatabase? = null

        fun getInstance(context: Context): ArtGalleryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArtGalleryDatabase::class.java,
                        "art_gallery_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}