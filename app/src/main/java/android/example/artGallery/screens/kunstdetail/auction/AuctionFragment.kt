package android.example.artGallery.screens.kunstdetail.auction

import android.example.artGallery.R
import android.example.artGallery.SliderImageAdapter
import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.databinding.FragmentKunstAuctionBinding
import android.example.artGallery.screens.kunstdetail.article.ArticleFragmentArgs
import android.example.artGallery.screens.kunstdetail.article.ArticleFragmentDirections
import android.example.artGallery.screens.kunstdetail.artwork.ArtworkFragmentArgs
import android.example.artGallery.screens.kunstdetail.artwork.ArtworkViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.smarteist.autoimageslider.SliderView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

class AuctionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentKunstAuctionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_kunst_auction, container, false)

        val appContext = requireNotNull(this.activity).application
        val dataSource = ArtGalleryDatabase.getInstance(appContext).artGalleryDAO

        val args = AuctionFragmentArgs.fromBundle(requireArguments())

        val auction = args.auctionId

        val viewModelFactory = AuctionViewModelFactory(dataSource, auction.id)

        val viewModel : AuctionViewModel by viewModels{viewModelFactory}

        binding.lifecycleOwner = this
        binding.auctionViewModel = viewModel

        viewModel.title.observe(this,  {
                newData -> binding.titelWerk.text = newData.toString()
        })

        viewModel.description.observe(this,  {
                newData -> binding.omschrijving.text = newData.toString()
        })

        viewModel.artist.observe(this,  {
                newData -> binding.artiest.text = newData.toString()
        })

        viewModel.offers.observe(this,  {
                newData -> binding.artiest.text =
            newData.maxWithOrNull(Comparator.comparingDouble { it.price }).toString()
        })

        binding.kopenButton.setOnClickListener{
            this.findNavController().navigate(
                AuctionFragmentDirections.actionAuctionFragmentToBiedenPopupFragment(auction))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageSlider = requireView().findViewById<SliderView>(R.id.imageSlider)
        var imageList: ArrayList<String>

        val args = AuctionFragmentArgs.fromBundle(requireArguments())

        val auction = args.auctionId

        imageList = (auction.pictures.map { pic ->
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