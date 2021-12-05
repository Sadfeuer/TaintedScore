package com.example.taintedscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.taintedscore.R
import com.example.taintedscore.data.ScoreData

class GamesAdapter(
    private val gamesList: List<ScoreData>,
    private val listener: OnItemClickListener
) :
    Adapter<GamesAdapter.GamesViewHolder>() {

    inner class GamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val povName: TextView = itemView.findViewById(R.id.recyclerItemName)
        val povTotalGames: TextView = itemView.findViewById(R.id.recyclerItemTotalGames)
        val povWinrate: TextView = itemView.findViewById(R.id.recyclerItemWinrate)
        val povImage: ImageView = itemView.findViewById(R.id.imageViewCover)
        val povButton: Button = itemView.findViewById(R.id.addPlayButton)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.single_item,
            parent, false
        )
        return GamesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val currentGame = gamesList[position]
        holder.povImage.setImageResource(currentGame.imageResource)
        holder.povName.text = currentGame.name
        holder.povWinrate.text = "Winrate is " + currentGame.winrate
        holder.povTotalGames.text = "Total games: " + 0
        holder.povButton.setOnClickListener {
            holder.povTotalGames.append("1")
            }
        }


    override fun getItemCount() = gamesList.size
}
