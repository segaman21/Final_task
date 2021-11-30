package com.example.bestapplication.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestapplication.R
import com.example.bestapplication.data.model.Actor

class ActorAdapter(private val items: List<Actor>) : RecyclerView.Adapter<ActorViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun onBind(item: Actor) {
        val nameActor = itemView.findViewById<TextView>(R.id.name_actor)
        val photoActor = itemView.findViewById<ImageView>(R.id.imageView_ironman)

        nameActor.text = item.name
        if (item.picture != null) {
            val imageUrl = "https://image.tmdb.org/t/p/original/${item.picture}"
            Glide.with(itemView)
                .load(imageUrl)
                .placeholder(R.drawable.arrow)
                .centerCrop()
                .into(photoActor)
        }
    }
}

