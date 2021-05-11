package com.kromer.news.presentation.ui.main.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kromer.news.databinding.ItemArticleBinding
import com.kromer.news.domain.model.Article
import com.kromer.news.utils.Utils

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemArticleBinding.bind(itemView)

    fun bind(item: Article, itemClick: (Article) -> Unit) {
        binding.ivPhoto.load(item.urlToImage)
        binding.tvTitle.text = item.title
        binding.tvDate.text = Utils.getDate(item.publishedAt!!)
        itemView.setOnClickListener {
            itemClick.invoke(item)
        }
    }
}