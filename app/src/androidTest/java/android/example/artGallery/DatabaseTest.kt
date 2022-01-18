package android.example.artGallery

import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.database.Artist
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var artGalleryDAO: ArtGalleryDAO
    private lateinit var db: ArtGalleryDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ArtGalleryDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        artGalleryDAO = db.artGalleryDAO
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertNewArtist(){
        val artist = Artist(1, "Jules", "Vervaeke", "ik ben jules en ik schilder graag")
        artGalleryDAO.insert(artist)
        val check = artGalleryDAO.getAllArtists()[0]
        //assertEquals(check?.firstName, "Jules")
        //assertEquals(check?.secondName, "Vervaeke")
        //assertEquals(check?.info, "ik ben jules en ik schilder graag")
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertEmptyArtist(){
        val artist = Artist()
        artGalleryDAO.insert(artist)
        val check = artGalleryDAO.getAllArtists()[0]
        //assertEquals(check?.firstName, "Jules")
        //assertEquals(check?.secondName, "Vervaeke")
        //assertEquals(check?.info, "ik ben jules en ik schilder graag")
    }


}