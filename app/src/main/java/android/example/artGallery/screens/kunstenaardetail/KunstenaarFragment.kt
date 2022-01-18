package android.example.artGallery.screens.kunstenaardetail

import android.example.artGallery.R
import android.example.artGallery.database.ArtGalleryDatabase
import android.example.artGallery.databinding.FragmentKunstenaarBinding
import android.example.artGallery.screens.kunstdetail.article.ArticleFragmentArgs
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso

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

        //use a factory to pass the database reference to the viewModel
        val viewModelFactory = KunstenaarViewModelFactory(dataSource, id)
        /*viewModel = ViewModelProvider(this, viewModelFactory).get(JokeViewModel::class.java)*/
        //use a delegate instead
        val viewModel : KunstenaarViewModel by viewModels{viewModelFactory}

        binding.lifecycleOwner = this
        binding.kunstenaarViewModel = viewModel
        binding.kunstenaarNaam.text =

            viewModel.fullName.observe(this, Observer { newData -> binding.kunstenaarNaam.text = newData.toString()
            }).toString()

        viewModel.picture.observe(this, Observer{
            newData -> Picasso.get().load(newData).into(binding.kunstenaarImageView)
        })

        //TODO: Kunstwerken

        return binding.root
    }
}