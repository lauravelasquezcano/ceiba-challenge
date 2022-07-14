package com.lauravelasquezcano.ceiba.app.ui.main.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lauravelasquezcano.ceiba.databinding.ItemUserBinding
import com.lauravelasquezcano.ceiba.domain.model.User
import javax.inject.Inject

class UsersAdapter @Inject constructor() :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var data: MutableList<User> = mutableListOf()

    fun setData(usersList: List<User>) {
        data.clear()
        data.addAll(usersList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = data[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = data.size

    inner class UsersViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                tvUserName.text = user.name
                tvTelephone.text = user.phone
                tvEmail.text = user.email
            }
        }
    }
}