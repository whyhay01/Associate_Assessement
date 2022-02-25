package com.example.associateassessment.adapter

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.associateassessment.R
import com.example.associateassessment.domain.Item

class FavoriteAdapter(val context: Context, private val favoriteUsers: List<Item>):
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.display_user,parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteUser = favoriteUsers[position]

        holder.favorite.visibility = View.GONE
        holder.profilePicture.load(favoriteUser.avatarUrl){
            placeholder(R.drawable.defualt_avatar)
            error(R.drawable.defualt_avatar)
        }
        holder.userName.text = favoriteUser.userName
    }

    override fun getItemCount(): Int = favoriteUsers.size

    inner class FavoriteViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){

        val profilePicture: ImageView = itemView.findViewById(R.id.profilePicture)
        val userName: TextView = itemView.findViewById(R.id.userName)
        val favorite: ImageButton = itemView.findViewById(R.id.btn_favorite)
    }
}