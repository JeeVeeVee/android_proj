package android.example.artGallery.screens.kunstenaardetail

import android.example.artGallery.R
import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.databinding.FragmentKunstenaarBinding
import android.example.artGallery.screens.home_artworks.ArtworksAdapter
import android.example.artGallery.screens.home_artworks.HomeKunstFragmentDirections
import android.example.artGallery.screens.kunstdetail.article.ArticleFragmentArgs
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_kunstenaar.*

/**
 * A simple [Fragment] subclass.
 * Use the [KunstenaarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KunstenaarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentKunstenaarBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_kunstenaar, container, false)

        val appContext = requireNotNull(this.activity).application
        val dataSource = ArtGalleryDatabase.getInstance(appContext).artGalleryDAO

        var args = KunstenaarFragmentArgs.fromBundle(requireArguments())

        val id: Long = args.artistId

        println(id)

        //use a factory to pass the database reference to the viewModel
        val viewModelFactory = KunstenaarViewModelFactory(dataSource, id)
        //use a delegate instead
        val viewModel: KunstenaarViewModel by viewModels { viewModelFactory }

        binding.lifecycleOwner = this
        binding.kunstenaarViewModel = viewModel
        binding.kunstenaarNaam.text = "naam"

        viewModel.fullName.observe(this, Observer { newData ->
            binding.kunstenaarNaam.text =viewModel.fullName.value
        }).toString()

        viewModel.picture.observe(this, Observer { newData ->
            Picasso.get().load(viewModel.picture.value).into(binding.kunstenaarImageView)
        })

        val adapter = ArtworksAdapter(ArtworksAdapter.ArtworksListener {
                artworkApi -> viewModel.onArtworkClicked(artworkApi)
        }, viewModel)

        viewModel.navigateToArtworkDetail.observe(viewLifecycleOwner, { artworkApi ->
            artworkApi?.let{

                if (artworkApi.type == "Article"){
                    this.findNavController().navigate(
                        HomeKunstFragmentDirections.actionHomeKunstFragmentToArticleFragment(artworkApi))
                    viewModel.onArtworkDetailNavigated()
                }
                else if (artworkApi.type == "Auction"){
                    this.findNavController().navigate(
                        HomeKunstFragmentDirections.actionHomeKunstFragmentToAuctionFragment(artworkApi))
                    viewModel.onArtworkDetailNavigated()
                }
                else{
                    this.findNavController().navigate(
                        KunstenaarFragmentDirections.actionKunstenaarToArtworkFragment(artworkApi))
                    viewModel.onArtworkDetailNavigated()
                }
            }
        })

        binding.artworksList.adapter = adapter

        viewModel.artworks.observe(this, {x -> x?.let {
            adapter.artworks = x
            adapter.notifyDataSetChanged()
        }}
        )

        val columns = resources.getInteger(android.example.artGallery.R.integer.gallery_columns)
        binding.artworksList.layoutManager = GridLayoutManager(requireContext(), columns)

        return binding.root
    }
}