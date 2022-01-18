package android.example.artGallery.screens.favorites

import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.databinding.FragmentFavoritesBinding
import android.example.artGallery.databinding.FragmentKunstHomeBinding
import android.example.artGallery.screens.home_artworks.ArtworkImageViewModel
import android.example.artGallery.screens.home_artworks.ArtworkImageViewModelFactory
import android.example.artGallery.screens.home_artworks.ArtworksAdapter
import android.example.artGallery.screens.home_artworks.HomeKunstFragmentDirections
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.*

class FavoritesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentFavoritesBinding = DataBindingUtil.inflate(
            inflater, android.example.artGallery.R.layout.fragment_favorites, container, false
        )


        val application = requireNotNull(this.activity).application
        val dataSource = ArtGalleryDatabase.getInstance(application).artGalleryDAO
        val viewModelFactory = FavoriteImageViewModelFactory(dataSource, application)

        val artworkImageViewModel = ViewModelProvider(
            this, viewModelFactory
        )[FavoriteImageViewModel::class.java]

        binding.favoritesViewModel = artworkImageViewModel

        binding.lifecycleOwner = this

        val viewModelJob = Job()

        val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        uiScope.launch {
            withContext(Dispatchers.IO) {


            }

        }

        artworkImageViewModel.navigateToArtworkDetail.observe(viewLifecycleOwner, { artworkApi ->
            artworkApi?.let {

                if (artworkApi.type == "Article") {
                    this.findNavController().navigate(
                        HomeKunstFragmentDirections.actionHomeKunstFragmentToArticleFragment(
                            artworkApi
                        )
                    )
                    artworkImageViewModel.onArtworkDetailNavigated()
                } else if (artworkApi.type == "Auction") {
                    this.findNavController().navigate(
                        HomeKunstFragmentDirections.actionHomeKunstFragmentToAuctionFragment(
                            artworkApi
                        )
                    )
                    artworkImageViewModel.onArtworkDetailNavigated()
                } else {
                    this.findNavController().navigate(
                        HomeKunstFragmentDirections.actionHomeKunstFragmentToArtworkFragment(
                            artworkApi
                        )
                    )
                    artworkImageViewModel.onArtworkDetailNavigated()
                }
            }
        })


        val adapter = FavoritesAdaptar(FavoritesAdaptar.ArtworksListener
        { artworkApi ->
            artworkImageViewModel.onArtworkClicked(artworkApi)
        }, artworkImageViewModel
        )

        binding.artworksList.adapter = adapter

        artworkImageViewModel.apiResponse.observe(viewLifecycleOwner, { x ->
            x?.let {
                adapter.artworks = x.artworks
                adapter.notifyDataSetChanged()
            }
        }
        )

        val columns = resources.getInteger(android.example.artGallery.R.integer.gallery_columns)
        binding.artworksList.layoutManager = GridLayoutManager(requireContext(), columns)



        return binding.root
    }


}