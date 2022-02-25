package com.example.associateassessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.associateassessment.R
import com.example.associateassessment.domain.Item

class UsersAdapter(private val context: Context,
                   private val itemList:List<Item> = emptyList(), private val block: (Int)->Unit, private val remove:(Int)->Unit):
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.display_user,parent,false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val item = itemList[position]

        holder.profilePicture.load(item.avatarUrl){
            placeholder(R.drawable.defualt_avatar)
            error(R.drawable.defualt_avatar)
        }

        holder.userName.text = item.userName
        holder.favorite.setOnClickListener {
            if (item.isFavorite){
                item.isFavorite = false
                holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                remove.invoke(position)
                Toast.makeText(context,"${item.userName} has been removed from your favorite",Toast.LENGTH_SHORT).show()
            }else {
                item.isFavorite = true
                holder.favorite.setImageResource(R.drawable.ic_favorite)
                block.invoke(position)
                Toast.makeText(context,"${item.userName} has been added to your favorite",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun getItemCount(): Int = itemList.size

    inner class UsersViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){

        val profilePicture: ImageView = itemView.findViewById(R.id.profilePicture)
        val userName:TextView = itemView.findViewById(R.id.userName)
        val favorite:ImageButton = itemView.findViewById(R.id.btn_favorite)


    }
}