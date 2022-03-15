package com.example.bestapplication.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestapplication.R
import com.example.bestapplication.data.model.Actor
import com.example.bestapplication.databinding.ViewHolderActorBinding
import com.example.bestapplication.utilites.Keys.POSTER_URL

class ActorAdapter(private val items: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    class ActorViewHolder(private val binding: ViewHolderActorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Actor) {
            binding.nameActor.text = item.name
            if (item.picture != null) {
                val imageUrl = POSTER_URL + item.picture
                Glide.with(itemView)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_download)
                    .centerCrop()
                    .into(binding.imageActor)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActorViewHolder {
        val view =
            ViewHolderActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ActorViewHolder,
        position: Int
    ) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}





