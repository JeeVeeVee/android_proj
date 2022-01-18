package android.example.artGallery.screens.kunstdetail.article

import android.example.artGallery.R
import android.example.artGallery.SliderImageAdapter
import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.databinding.FragmentKunstArticleBinding
import android.example.artGallery.screens.home_artworks.HomeKunstFragmentDirections
import android.example.artGallery.screens.kunstdetail.artwork.ArtworkFragmentArgs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.smarteist.autoimageslider.SliderView


class ArticleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentKunstArticleBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_kunst_article, container, false)

        val appContext = requireNotNull(this.activity).application
        val dataSource = ArtGalleryDatabase.getInstance(appContext).artGalleryDAO

        val args = ArticleFragmentArgs.fromBundle(requireArguments())

        val article = args.articleId

        val viewModelFactory = ArticleViewModelFactory(dataSource, article.id)
        val viewModel : ArticleViewModel by viewModels{viewModelFactory}

        binding.lifecycleOwner = this
        binding.articleViewModel = viewModel

        viewModel.title.observe(this, {
            binding.titelWerk.text = article.title
        })

        viewModel.description.observe(this, {
            binding.omschrijving.text = article.description
        })

        viewModel.artist.observe(this, {
            binding.artiest.text = article.artist.firstName + " " + article.artist.lastName
        })

        viewModel.price.observe(this, {
            binding.prijsLabel.text = article.toString()
        })

        binding.kopenButton.setOnClickListener{
            this.findNavController().navigate(
                ArticleFragmentDirections.actionArticleFragmentToKopenPopupFragment(article))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageSlider = requireView().findViewById<SliderView>(R.id.imageSlider)
        var imageList: ArrayList<String>

        val args = ArticleFragmentArgs.fromBundle(requireArguments())

        val article = args.articleId

        imageList = (article.pictures.map { pic ->
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