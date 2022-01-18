package android.example.artGallery

import android.example.artGallery.network.HooopApi
import android.util.Log
import android.util.Log.INFO
import androidx.lifecycle.viewModelScope
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class APITest {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    @Test
    fun getMarsRealEstateProperties() {
        uiScope.launch {
            withContext(Dispatchers.IO){
                try {
                    var artworks = HooopApi.retrofitService.getArtWorks()
                    Log.println(1, "test", artworks.toString())
                } catch (e: Exception) {
                }
            }
        }
    }
}