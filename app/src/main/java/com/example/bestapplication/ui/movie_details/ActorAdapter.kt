package com.example.bestapplication.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestapplication.R
import com.example.bestapplication.data.model.Actor
import com.example.bestapplication.databinding.ViewHolderActorBinding

class ActorAdapter(private val items: List<Actor>) : RecyclerView.Adapter<ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = ViewHolderActorBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ActorViewHolder(private val binding: ViewHolderActorBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Actor) {
        binding.nameActor.text = item.name
        if (item.picture != null) {
            val imageUrl = "https://image.tmdb.org/t/p/original/${item.picture}"
            Glide.with(itemView)
                .load(imageUrl)
                .placeholder(R.drawable.arrow)
                .centerCrop()
                .into(binding.imageActor)
        }
    }
}

