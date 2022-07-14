package com.lauravelasquezcano.ceiba.app.ui.main.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lauravelasquezcano.ceiba.databinding.ItemPostBinding
import com.lauravelasquezcano.ceiba.domain.model.Post
import javax.inject.Inject

class PostsAdapter @Inject constructor() :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private var data: MutableList<Post> = mutableListOf()

    fun setData(postsList: List<Post>) {
        data.clear()
        data.addAll(postsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder =
        PostsViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = data[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = data.size

    inner class PostsViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            with(binding) {
                tvPostTitle.text = post.title
                tvPostBody.text = post.body
            }
        }
    }
}