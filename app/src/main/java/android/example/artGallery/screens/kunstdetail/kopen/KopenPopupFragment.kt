package android.example.artGallery.screens.kunstdetail.kopen

import android.example.artGallery.R
import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.databinding.FragmentKopenPopupBinding
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

class KopenPopupFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentKopenPopupBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_kopen_popup, container, false)

        val appContext = requireNotNull(this.activity).application
        val dataSource = ArtGalleryDatabase.getInstance(appContext).artGalleryDAO

        val args = KopenPopupFragmentArgs.fromBundle(requireArguments())

        val article = args.articleId

        val viewModelFactory = KopenViewModelFactory(dataSource, article.id)

        val viewModel : KopenViewModel by viewModels{viewModelFactory}

        binding.lifecycleOwner = this
       // binding.kopenViewModel = viewModel

        viewModel.title.observe(this, {
                newData -> binding.artworkName.text = newData.toString()
        })

        viewModel.artist.observe(this, {
                newData -> binding.artistName.text = newData.toString()
        })

        viewModel.price.observe(this, {
            newData -> binding.price.text = "€$newData"
        })

        binding.buyButton.setOnClickListener{
            viewModel.CreateOrder()
            Toast.makeText(context, "Order for " + viewModel.title.value + " has been placed.", 2)
            this.findNavController().navigate(
                KopenPopupFragmentDirections.actionKopenPopupFragmentToArticleFragment(article))
        }


        return binding.root
    }
}