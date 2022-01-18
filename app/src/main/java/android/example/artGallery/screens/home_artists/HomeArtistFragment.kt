package android.example.artGallery.screens.home_artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.artGallery.R
import android.example.artGallery.database.ArtGalleryDAO
import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.databinding.FragmentArtistListBinding
import android.example.artGallery.databinding.FragmentLoginBinding
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


class HomeArtistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding : FragmentArtistListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_artist_list, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = ArtGalleryDatabase.getInstance(application).artGalleryDAO
        val viewModelFactory = ArtistViewModelFactory(dataSource, application)

        val artistViewModel = ViewModelProvider(this, viewModelFactory)[ArtistViewModel::class.java]

        binding.artistViewModel = artistViewModel

        binding.lifecycleOwner = this

        artistViewModel.navigateToArtistDetail.observe(viewLifecycleOwner, {artistId ->
            artistId?.let{
                this.findNavController().navigate(
                    HomeArtistFragmentDirections.actionArtistListFragmentToKunstenaar(artistId))
                artistViewModel.onArtworkDetailNavigated()

            }
        })

        val adapter = ArtistAdapter(ArtistAdapter.ArtistClickListener{
            artistId -> artistViewModel.onArtistClicked(artistId)
        }, artistViewModel)
        binding.artistList.adapter = adapter

        artistViewModel.apiResponse.observe(viewLifecycleOwner, {x -> x?.let {
            adapter.artists = x
            println("De artists in de adapter: ${adapter.artists}")
            adapter.notifyDataSetChanged()
        }})

        return binding.root

    }
}