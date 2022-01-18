package android.example.artGallery.screens.kunstenaardetail

import android.example.artGallery.database.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*


class KunstenaarViewModel(val database: ArtGalleryDAO, val id: Long) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> get() = _fullName

    private val _picture = MutableLiveData<String>()
    val picture: LiveData<String> get() = _picture

    init{
         val viewModelJob = Job()

         val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

/*        uiScope.launch {
            withContext(Dispatchers.IO) {

                val artist = database.getArtistByKey(id)

                _email.value = artist.email
                _fullName.value = artist.firstName + " " + artist.secondName
                _picture.value = artist.picture
            }
        }*/
    }
}