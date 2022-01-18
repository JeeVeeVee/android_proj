package android.example.artGallery.screens.kunstdetail.artwork

import android.example.artGallery.R
import android.example.artGallery.SliderImageAdapter
import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.database.favorites.Favorite
import android.example.artGallery.database.favorites.FavoriteDatabase
import android.example.artGallery.databinding.FragmentKunstArtworkBinding
import android.example.artGallery.screens.kunstdetail.article.ArticleFragmentArgs
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_kunst_artwork.*
import kotlinx.coroutines.*


class ArtworkFragment : Fragment() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentKunstArtworkBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_kunst_artwork, container, false)

        val appContext = requireNotNull(this.activity).application
        val dataSource = ArtGalleryDatabase.getInstance(appContext).artGalleryDAO
        val dataSource1 = FavoriteDatabase.getInstance(appContext).favoriteDAO
        val args = ArtworkFragmentArgs.fromBundle(requireArguments())

        val artwork = args.artworkId
        //use a factory to pass the database reference to the viewModel
        val viewModelFactory = ArtworkViewModelFactory(dataSource, artwork.id, dataSource1)

        val viewModel : ArtworkViewModel by viewModels{viewModelFactory}

        binding.lifecycleOwner = this
        binding.artworkViewModel = viewModel 

        binding.titelWerk.text = artwork.title
        binding.omschrijving.text = artwork.description
        binding.artiest.text = artwork.artist.firstName + " " + artwork.artist.lastName

        viewModel.title.observe(this, {
            binding.titelWerk.text = artwork.title
        })

        viewModel.description.observe(this, {
            binding.omschrijving.text = artwork.description
        })

        viewModel.artist.observe(this, {
            binding.artiest.text = artwork.artist.firstName + " " + artwork.artist.lastName
        })
        if(viewModel.isFavorite.value == true){
            binding.buttonFavorite.setText("favoriet verwijderen")
        } else {
            binding.buttonFavorite.setText("favoriet toevoegen")
        }

        binding.buttonFavorite.setOnClickListener{
            if(viewModel.isFavorite.value == true){
                viewModel.removeFav(id)
                binding.buttonFavorite.setText("favoriet toevoegen")
            } else {
                viewModel.addFav(id)
                binding.buttonFavorite.setText("favoriet verwijderen")
            }
        }


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageSlider = requireView().findViewById<SliderView>(R.id.imageSlider)
        var imageList: ArrayList<String>

        val args = ArtworkFragmentArgs.fromBundle(requireArguments())

        val artwork = args.artworkId

        imageList = (artwork.pictures.map { pic ->
            pic.picture
        } as ArrayList<String>)

        setImagesInSlider(imageList, imageSlider)
    }
    private fun setImagesInSlider(images: ArrayList<String>, imageSlider: SliderView){
        val adapter = SliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = false
    }


}