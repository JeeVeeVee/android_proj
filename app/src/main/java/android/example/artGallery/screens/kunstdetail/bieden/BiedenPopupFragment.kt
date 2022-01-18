package android.example.artGallery.screens.kunstdetail.bieden

import android.example.artGallery.R
import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.databinding.FragmentBiedenPopupBinding
import android.example.artGallery.screens.kunstdetail.auction.AuctionFragmentDirections
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController


class BiedenPopupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentBiedenPopupBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bieden_popup, container, false)

        val appContext = requireNotNull(this.activity).application
        val dataSource = ArtGalleryDatabase.getInstance(appContext).artGalleryDAO

        val args = BiedenPopupFragmentArgs.fromBundle(requireArguments())

        val auction = args.auctionId

        val viewModelFactory = BiedenViewModelFactory(dataSource, auction.id)

        val viewModel : BiedenViewModel by viewModels{viewModelFactory}

        binding.lifecycleOwner = this
        binding.biedenViewModel = viewModel

        viewModel.title.observe(this, {
                newData -> binding.artworkName.text = newData.toString()
        })

        viewModel.artist.observe(this, {
                newData -> binding.artistName.text = newData.toString()
        })

        viewModel.offers.observe(this, {
            newData -> binding.price.text = newData.maxWithOrNull(Comparator.comparingDouble { it.price })?.price.toString()
        })

        binding.offerButton.setOnClickListener{
            viewModel.CreateOffer(binding.offerInput.text as Double)
            Toast.makeText(context, "Offer for " + viewModel.title.value + " has been placed.", 2)
            this.findNavController().navigate(
                BiedenPopupFragmentDirections.actionBiedenPopupFragmentToAuctionFragment(auction))
        }

        return binding.root
    }
}