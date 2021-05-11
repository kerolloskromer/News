package com.kromer.news.presentation.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.kromer.news.databinding.FragmentDetailsBinding
import com.kromer.news.domain.model.Article
import com.kromer.news.presentation.base.BaseFragment
import com.kromer.news.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val viewModel: DetailsViewModel by viewModels()
    private var article: Article? = null

    override fun getVBInflater(): (LayoutInflater) -> FragmentDetailsBinding =
        FragmentDetailsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtras()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(article)
    }

    private fun getExtras() {
        val args = DetailsFragmentArgs.fromBundle(requireArguments())
        article = args.article
    }

    private fun setData(item: Article?) {
        binding.ivPhoto.load(item?.urlToImage)
        binding.tvTitle.text = item?.title
        binding.tvDescription.text = item?.description
        binding.tvBy.text = item?.source?.name
        binding.tvDate.text = Utils.getDate(item?.publishedAt!!)

        binding.btnOpenSource.setOnClickListener {
            Utils.openBrowser(item?.url!!, requireContext())
        }
    }
}
